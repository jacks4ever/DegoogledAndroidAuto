package com.degoogled.androidauto.ui.messaging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.degoogled.androidauto.data.model.Conversation
import com.degoogled.androidauto.data.model.Message
import com.degoogled.androidauto.data.model.User
import com.degoogled.androidauto.data.repository.MessagingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.Date

@ExperimentalCoroutinesApi
class MessagingViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    
    private val testDispatcher = TestCoroutineDispatcher()
    
    private lateinit var viewModel: MessagingViewModel
    private lateinit var messagingRepository: MessagingRepository
    
    private val conversations = listOf(
        Conversation(
            id = "1",
            name = "John Doe",
            participants = listOf(User(id = "user1", name = "John Doe")),
            lastMessage = Message(
                id = "msg1",
                conversationId = "1",
                senderId = "user1",
                content = "Hello",
                timestamp = Date(),
                isRead = true
            ),
            unreadCount = 0
        ),
        Conversation(
            id = "2",
            name = "Jane Smith",
            participants = listOf(User(id = "user2", name = "Jane Smith")),
            lastMessage = Message(
                id = "msg2",
                conversationId = "2",
                senderId = "user2",
                content = "Hi there",
                timestamp = Date(),
                isRead = false
            ),
            unreadCount = 1
        )
    )
    
    private val messages = mapOf(
        "1" to listOf(
            Message(
                id = "msg1",
                conversationId = "1",
                senderId = "user1",
                content = "Hello",
                timestamp = Date(),
                isRead = true
            )
        ),
        "2" to listOf(
            Message(
                id = "msg2",
                conversationId = "2",
                senderId = "user2",
                content = "Hi there",
                timestamp = Date(),
                isRead = false
            )
        )
    )
    
    private val _conversations = MutableStateFlow<List<Conversation>>(conversations)
    private val _messages = MutableStateFlow<Map<String, List<Message>>>(messages)
    
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        
        messagingRepository = mock()
        
        whenever(messagingRepository.getConversations()).thenReturn(_conversations as StateFlow<List<Conversation>>)
        whenever(messagingRepository.getMessages()).thenReturn(_messages as StateFlow<Map<String, List<Message>>>)
        
        viewModel = MessagingViewModel(messagingRepository)
    }
    
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
    
    @Test
    fun `initial state should be correct`() {
        assert(viewModel.conversations.value == conversations)
        assert(viewModel.selectedConversation.value == null)
        assert(viewModel.currentMessages.value.isEmpty())
    }
    
    @Test
    fun `selectConversation should update selectedConversation and currentMessages`() {
        // Given
        val conversation = conversations[0]
        
        // When
        viewModel.selectConversation(conversation)
        
        // Then
        assert(viewModel.selectedConversation.value == conversation)
        assert(viewModel.currentMessages.value == messages["1"])
    }
    
    @Test
    fun `sendMessage should call repository`() = testDispatcher.runBlockingTest {
        // Given
        val conversation = conversations[0]
        val content = "Hello there"
        viewModel.selectConversation(conversation)
        
        // Mock the repository response
        val sentMessage = Message(
            id = "msg3",
            conversationId = "1",
            senderId = "me",
            content = content,
            timestamp = Date(),
            isRead = true
        )
        whenever(messagingRepository.sendMessage(any(), any())).thenReturn(Result.success(sentMessage))
        
        // When
        viewModel.sendMessage(content)
        
        // Then
        verify(messagingRepository).sendMessage("1", content)
    }
    
    @Test
    fun `markAsRead should call repository`() = testDispatcher.runBlockingTest {
        // Given
        val messageId = "msg2"
        whenever(messagingRepository.markAsRead(messageId)).thenReturn(Result.success(Unit))
        
        // When
        viewModel.markAsRead(messageId)
        
        // Then
        verify(messagingRepository).markAsRead(messageId)
    }
    
    @Test
    fun `repository updates should be reflected in view model`() {
        // Given
        val newConversation = Conversation(
            id = "3",
            name = "Bob Johnson",
            participants = listOf(User(id = "user3", name = "Bob Johnson")),
            lastMessage = Message(
                id = "msg3",
                conversationId = "3",
                senderId = "user3",
                content = "Hey",
                timestamp = Date(),
                isRead = true
            ),
            unreadCount = 0
        )
        
        // When
        _conversations.value = conversations + newConversation
        
        // Then
        assert(viewModel.conversations.value.size == 3)
        assert(viewModel.conversations.value.last() == newConversation)
    }
    
    @Test
    fun `searchConversations should filter conversations`() {
        // Given
        val query = "John"
        
        // When
        viewModel.searchConversations(query)
        
        // Then
        assert(viewModel.filteredConversations.value?.size == 1)
        assert(viewModel.filteredConversations.value?.get(0)?.name == "John Doe")
    }
    
    @Test
    fun `searchConversations with empty query should show all conversations`() {
        // Given
        val query = ""
        
        // When
        viewModel.searchConversations(query)
        
        // Then
        assert(viewModel.filteredConversations.value?.size == 2)
    }
}