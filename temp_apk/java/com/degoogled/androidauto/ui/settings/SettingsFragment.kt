package com.degoogled.androidauto.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.degoogled.androidauto.databinding.FragmentSettingsBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment for settings.
 */
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: SettingsViewModel by viewModel()
    private lateinit var settingsAdapter: SettingsAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        observeViewModel()
    }
    
    private fun setupRecyclerView() {
        settingsAdapter = SettingsAdapter(
            onToggleClick = { setting, isEnabled ->
                viewModel.updateSetting(setting.id, isEnabled)
            },
            onItemClick = { setting ->
                if (!setting.isToggle) {
                    // Handle click for non-toggle settings
                    when (setting.id) {
                        "download_maps" -> viewModel.downloadMaps()
                        "clear_cache" -> viewModel.clearCache()
                        "about" -> showAboutInfo()
                    }
                }
            }
        )
        
        binding.recyclerSettings.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = settingsAdapter
        }
    }
    
    private fun observeViewModel() {
        viewModel.settings.observe(viewLifecycleOwner) { settings ->
            settingsAdapter.submitList(settings)
        }
        
        viewModel.message.observe(viewLifecycleOwner) { message ->
            if (message.isNotEmpty()) {
                Snackbar.make(
                    binding.root,
                    message,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
    
    private fun showAboutInfo() {
        // In a real implementation, this would show an about dialog
        Snackbar.make(
            binding.root,
            "Degoogled Android Auto - Open Source, Privacy-Focused Car Interface",
            Snackbar.LENGTH_LONG
        ).show()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}