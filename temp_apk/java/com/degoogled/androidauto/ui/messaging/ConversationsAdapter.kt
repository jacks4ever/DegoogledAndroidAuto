package com.degoogled.androidauto.ui.messaging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.degoogled.androidauto.R
import com.degoogled.androidauto.data.model.Conversation
import com.degoogled.androidauto.databinding.ItemConversationBinding
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Adapter for the conversations list.
 */
class ConversationsAdapter(
    private val onItemClick: (Conversation) -> Unit
) : ListAdapter<Conversation, ConversationsAdapter.ConversationViewHolder>(ConversationDiffCallback()) {

    private val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val binding = ItemConversationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ConversationViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class ConversationViewHolder(
        private val binding: ItemConversationBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }
        }
        
        fun bind(conversation: Conversation) {
            val contact = conversation.participants.firstOrNull()
            binding.textContactName.text = contact?.name ?: "Unknown"
            
            val lastMessage = conversation.lastMessage
            if (lastMessage != null) {
                binding.textLastMessage.text = lastMessage.content
                binding.textTimestamp.text = timeFormat.format(lastMessage.timestamp)
            } else {
                binding.textLastMessage.text = ""
                binding.textTimestamp.text = ""
            }
            
            if (conversation.unreadCount > 0) {
                binding.badgeUnread.visibility = View.VISIBLE
                binding.badgeUnread.text = conversation.unreadCount.toString()
            } else {
                binding.badgeUnread.visibility = View.GONE
            }
            
            // In a real implementation, this would load the contact's avatar
            // For now, just use the person icon
            binding.imageAvatar.setImageResource(R.drawable.ic_person)
        }
    }
    
    private class ConversationDiffCallback : DiffUtil.ItemCallback<Conversation>() {
        override fun areItemsTheSame(oldItem: Conversation, newItem: Conversation): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Conversation, newItem: Conversation): Boolean {
            return oldItem == newItem
        }
    }
}