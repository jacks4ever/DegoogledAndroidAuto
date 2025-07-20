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
 * Fragment for displaying the media library.
 */
class MediaLibraryFragment : Fragment() {

    private var _binding: FragmentMediaLibraryBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: MediaViewModel by sharedViewModel()
    private lateinit var mediaAdapter: MediaAdapter
    
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
        mediaAdapter = MediaAdapter { media ->
            viewModel.playMedia(media)
        }
        
        binding.recyclerMedia.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mediaAdapter
        }
    }
    
    private fun observeViewModel() {
        viewModel.mediaLibrary.observe(viewLifecycleOwner) { mediaList ->
            mediaAdapter.submitList(mediaList)
            
            if (mediaList.isEmpty()) {
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