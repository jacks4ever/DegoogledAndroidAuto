package com.degoogled.androidauto.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.degoogled.androidauto.R
import com.degoogled.androidauto.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Home screen fragment showing the main menu options.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var menuAdapter: MainMenuAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        observeViewModel()
    }
    
    private fun setupRecyclerView() {
        menuAdapter = MainMenuAdapter { menuItem ->
            when (menuItem.id) {
                "navigation" -> findNavController().navigate(R.id.action_homeFragment_to_navigationFragment)
                "media" -> findNavController().navigate(R.id.action_homeFragment_to_mediaFragment)
                "messaging" -> findNavController().navigate(R.id.action_homeFragment_to_messagingFragment)
                "settings" -> findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
            }
        }
        
        binding.recyclerMainMenu.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = menuAdapter
        }
    }
    
    private fun observeViewModel() {
        viewModel.menuItems.observe(viewLifecycleOwner) { menuItems ->
            menuAdapter.submitList(menuItems)
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}