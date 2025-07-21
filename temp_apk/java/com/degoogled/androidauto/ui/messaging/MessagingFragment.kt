package com.degoogled.androidauto.ui.messaging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.degoogled.androidauto.R
import com.degoogled.androidauto.databinding.FragmentMessagingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment for messaging.
 */
class MessagingFragment : Fragment() {

    private var _binding: FragmentMessagingBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: MessagingViewModel by viewModel()
    private lateinit var conversationsAdapter: ConversationsAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessagingBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        observeViewModel()
    }
    
    private fun setupRecyclerView() {
        conversationsAdapter = ConversationsAdapter { conversation ->
            // Navigate to conversation detail
            val bundle = Bundle().apply {
                putString("conversationId", conversation.id)
            }
            findNavController().navigate(R.id.action_messagingFragment_to_conversationFragment, bundle)
        }
        
        binding.recyclerConversations.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = conversationsAdapter
        }
    }
    
    private fun observeViewModel() {
        viewModel.conversations.observe(viewLifecycleOwner) { conversations ->
            conversationsAdapter.submitList(conversations)
            
            if (conversations.isEmpty()) {
                binding.textEmpty.visibility = View.VISIBLE
                binding.recyclerConversations.visibility = View.GONE
            } else {
                binding.textEmpty.visibility = View.GONE
                binding.recyclerConversations.visibility = View.VISIBLE
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}