package com.degoogled.androidauto.data.messaging

import android.content.Context
import android.util.Log
import com.degoogled.androidauto.data.model.Conversation
import com.degoogled.androidauto.data.model.Message
import com.degoogled.androidauto.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import org.matrix.android.sdk.api.Matrix
import org.matrix.android.sdk.api.MatrixConfiguration
import org.matrix.android.sdk.api.auth.data.HomeServerConnectionConfig
import org.matrix.android.sdk.api.session.Session
import org.matrix.android.sdk.api.session.events.model.EventType
import org.matrix.android.sdk.api.session.events.model.toModel
import org.matrix.android.sdk.api.session.room.model.message.MessageContent
import org.matrix.android.sdk.api.session.room.model.message.MessageType
import org.matrix.android.sdk.api.session.room.timeline.Timeline
import org.matrix.android.sdk.api.session.room.timeline.TimelineEvent
import java.util.Date
import java.util.UUID

/**
 * Client for Matrix messaging protocol.
 */
class MatrixClient(private val context: Context) {

    private var matrix: Matrix? = null
    private var session: Session? = null
    
    private val _conversations = MutableStateFlow<List<Conversation>>(emptyList())
    val conversations: StateFlow<List<Conversation>> = _conversations
    
    private val _messages = MutableStateFlow<Map<String, List<Message>>>(emptyMap())
    val messages: StateFlow<Map<String, List<Message>>> = _messages
    
    private val roomTimelines = mutableMapOf<String, Timeline>()
    
    suspend fun initialize(homeServerUrl: String, username: String, password: String): Boolean = withContext(Dispatchers.IO) {
        try {
            if (matrix == null) {
                matrix = Matrix(
                    context = context,
                    matrixConfiguration = MatrixConfiguration(
                        applicationFlavor = "DegoogledAndroidAuto",
                        cryptoConfig = null
                    )
                )
            }
            
            if (session == null) {
                val homeServerConfig = HomeServerConnectionConfig.Builder()
                    .withHomeServerUri(homeServerUrl)
                    .build()
                
                // Try to restore an existing session
                val lastSession = matrix?.authenticationService()?.getLastAuthenticated()
                if (lastSession != null) {
                    session = lastSession
                } else {
                    // Login with username and password
                    session = matrix?.authenticationService()?.directAuthentication(
                        homeServerConnectionConfig = homeServerConfig,
                        userId = username,
                        password = password,
                        initialDeviceName = "DegoogledAndroidAuto"
                    )
                }
                
                if (session != null) {
                    session?.open()
                    session?.syncService()?.startSync(true)
                    
                    // Start listening for room updates
                    listenForRoomUpdates()
                    
                    // Load initial conversations
                    loadConversations()
                }
            }
            
            session != null
        } catch (e: Exception) {
            Log.e(TAG, "Matrix initialization failed", e)
            false
        }
    }
    
    private fun listenForRoomUpdates() {
        session?.roomService()?.getrooms()?.forEach { room ->
            val timeline = room.timelineService().createTimeline(null, Timeline.Direction.BACKWARDS)
            timeline.addListener(object : Timeline.Listener {
                override fun onTimelineUpdated(snapshot: List<TimelineEvent>) {
                    // Update messages for this room
                    updateMessagesForRoom(room.roomId)
                }
            })
            roomTimelines[room.roomId] = timeline
            timeline.start()
        }
    }
    
    private fun loadConversations() {
        val rooms = session?.roomService()?.getrooms() ?: return
        
        val conversations = rooms.map { room ->
            val roomSummary = room.roomSummary()
            val otherParticipants = room.membershipService().getRoomMembers().filter {
                it.userId != session?.myUserId
            }
            
            val lastEvent = roomSummary?.latestPreviewableEvent
            val lastMessage = if (lastEvent != null) {
                val messageContent = lastEvent.root.getClearContent().toModel<MessageContent>()
                if (messageContent?.type == MessageType.MSGTYPE_TEXT) {
                    Message(
                        id = lastEvent.eventId,
                        conversationId = room.roomId,
                        senderId = lastEvent.root.senderId ?: "",
                        content = messageContent.body ?: "",
                        timestamp = Date(lastEvent.root.originServerTs ?: 0),
                        isRead = true
                    )
                } else null
            } else null
            
            val unreadCount = roomSummary?.notificationCount ?: 0
            
            Conversation(
                id = room.roomId,
                name = roomSummary?.displayName ?: "Unknown",
                participants = otherParticipants.map { member ->
                    User(
                        id = member.userId,
                        name = member.displayName ?: member.userId
                    )
                },
                lastMessage = lastMessage,
                unreadCount = unreadCount
            )
        }
        
        _conversations.value = conversations
    }
    
    private fun updateMessagesForRoom(roomId: String) {
        val room = session?.roomService()?.getRoom(roomId) ?: return
        val timeline = roomTimelines[roomId] ?: return
        
        val messages = timeline.getSnapshot().mapNotNull { event ->
            if (event.root.type == EventType.MESSAGE) {
                val messageContent = event.root.getClearContent().toModel<MessageContent>()
                if (messageContent?.type == MessageType.MSGTYPE_TEXT) {
                    Message(
                        id = event.eventId,
                        conversationId = roomId,
                        senderId = event.root.senderId ?: "",
                        content = messageContent.body ?: "",
                        timestamp = Date(event.root.originServerTs ?: 0),
                        isRead = true
                    )
                } else null
            } else null
        }
        
        val currentMessages = _messages.value.toMutableMap()
        currentMessages[roomId] = messages
        _messages.value = currentMessages
        
        // Also update conversations to reflect any changes
        loadConversations()
    }
    
    suspend fun getMessages(conversationId: String): List<Message> = withContext(Dispatchers.IO) {
        val room = session?.roomService()?.getRoom(conversationId) ?: return@withContext emptyList()
        
        if (!roomTimelines.containsKey(conversationId)) {
            val timeline = room.timelineService().createTimeline(null, Timeline.Direction.BACKWARDS)
            timeline.addListener(object : Timeline.Listener {
                override fun onTimelineUpdated(snapshot: List<TimelineEvent>) {
                    updateMessagesForRoom(conversationId)
                }
            })
            roomTimelines[conversationId] = timeline
            timeline.start()
        }
        
        // Wait for messages to load
        var attempts = 0
        while (!_messages.value.containsKey(conversationId) && attempts < 10) {
            kotlinx.coroutines.delay(100)
            attempts++
        }
        
        _messages.value[conversationId] ?: emptyList()
    }
    
    suspend fun sendMessage(recipientId: String, content: String): Result<Message> = withContext(Dispatchers.IO) {
        try {
            // Find or create a direct chat room with the recipient
            val existingRoom = session?.roomService()?.getRoomSummaries()?.find { roomSummary ->
                roomSummary.isDirect && roomSummary.otherMemberIds.contains(recipientId)
            }
            
            val roomId = if (existingRoom != null) {
                existingRoom.roomId
            } else {
                // Create a new direct chat room
                session?.roomService()?.createDirectRoom(recipientId)
            }
            
            if (roomId != null) {
                val room = session?.roomService()?.getRoom(roomId)
                if (room != null) {
                    val eventId = room.sendService().sendTextMessage(content)
                    
                    // Create a message object
                    val message = Message(
                        id = eventId,
                        conversationId = roomId,
                        senderId = session?.myUserId ?: "",
                        content = content,
                        timestamp = Date(),
                        isRead = true
                    )
                    
                    Result.success(message)
                } else {
                    Result.failure(Exception("Room not found"))
                }
            } else {
                Result.failure(Exception("Failed to create or find room"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "Send message failed", e)
            Result.failure(e)
        }
    }
    
    suspend fun markAsRead(messageId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            // Find the room containing this message
            val message = _messages.value.values.flatten().find { it.id == messageId }
            if (message != null) {
                val room = session?.roomService()?.getRoom(message.conversationId)
                if (room != null) {
                    room.readService().markAsRead(ReadService.MarkAsReadParams.READ_RECEIPT)
                    Result.success(Unit)
                } else {
                    Result.failure(Exception("Room not found"))
                }
            } else {
                Result.failure(Exception("Message not found"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "Mark as read failed", e)
            Result.failure(e)
        }
    }
    
    fun logout() {
        roomTimelines.values.forEach { it.dispose() }
        roomTimelines.clear()
        
        session?.signOutService()?.signOut(true)
        session?.close()
        session = null
    }
    
    companion object {
        private const val TAG = "MatrixClient"
    }
}