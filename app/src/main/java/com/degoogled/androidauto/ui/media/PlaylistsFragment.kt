package com.degoogled.androidauto.ui.media

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.degoogled.androidauto.databinding.FragmentMediaLibraryBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Fragment for displaying playlists.
 */
class PlaylistsFragment : Fragment() {

    private var _binding: FragmentMediaLibraryBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: MediaViewModel by sharedViewModel()
    private lateinit var playlistAdapter: PlaylistAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMediaLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        observeViewModel()
    }
    
    private fun setupRecyclerView() {
        playlistAdapter = PlaylistAdapter { playlist ->
            // In a real implementation, this would show the playlist contents
            // For now, if the playlist has items, play the first one
            if (playlist.items.isNotEmpty()) {
                viewModel.playMedia(playlist.items.first())
            }
        }
        
        binding.recyclerMedia.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = playlistAdapter
        }
    }
    
    private fun observeViewModel() {
        viewModel.playlists.observe(viewLifecycleOwner) { playlists ->
            playlistAdapter.submitList(playlists)
            
            if (playlists.isEmpty()) {
                binding.textEmpty.visibility = View.VISIBLE
                binding.recyclerMedia.visibility = View.GONE
            } else {
                binding.textEmpty.visibility = View.GONE
                binding.recyclerMedia.visibility = View.VISIBLE
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}