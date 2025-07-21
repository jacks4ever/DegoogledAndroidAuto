package com.degoogled.androidauto.ui.messaging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.degoogled.androidauto.databinding.FragmentConversationBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * Fragment for a conversation.
 */
class ConversationFragment : Fragment() {

    private var _binding: FragmentConversationBinding? = null
    private val binding get() = _binding!!
    
    private val conversationId: String by lazy {
        arguments?.getString("conversationId") ?: ""
    }
    
    private val viewModel: ConversationViewModel by viewModel { parametersOf(conversationId) }
    private lateinit var messagesAdapter: MessagesAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConversationBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupMessageInput()
        observeViewModel()
    }
    
    private fun setupRecyclerView() {
        messagesAdapter = MessagesAdapter()
        
        binding.recyclerMessages.apply {
            layoutManager = LinearLayoutManager(requireContext()).apply {
                stackFromEnd = true
            }
            adapter = messagesAdapter
        }
    }
    
    private fun setupMessageInput() {
        binding.buttonSend.setOnClickListener {
            val message = binding.editMessage.text.toString().trim()
            if (message.isNotEmpty()) {
                viewModel.sendMessage(message)
                binding.editMessage.text.clear()
            }
        }
    }
    
    private fun observeViewModel() {
        viewModel.messages.observe(viewLifecycleOwner) { messages ->
            messagesAdapter.submitList(messages) {
                if (messages.isNotEmpty()) {
                    binding.recyclerMessages.scrollToPosition(messages.size - 1)
                }
            }
        }
        
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage.isNotEmpty()) {
                Snackbar.make(
                    binding.root,
                    errorMessage,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}