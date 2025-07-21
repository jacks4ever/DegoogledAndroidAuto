package com.degoogled.androidauto.ui.media

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.degoogled.androidauto.R
import com.degoogled.androidauto.data.repository.PlaybackState
import com.degoogled.androidauto.databinding.FragmentMediaBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment for media playback.
 */
class MediaFragment : Fragment() {

    private var _binding: FragmentMediaBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: MediaViewModel by viewModel()
    private lateinit var pagerAdapter: MediaPagerAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMediaBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViewPager()
        setupMediaControls()
        observeViewModel()
    }
    
    private fun setupViewPager() {
        pagerAdapter = MediaPagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter
        
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.library)
                1 -> getString(R.string.playlists)
                else -> null
            }
        }.attach()
    }
    
    private fun setupMediaControls() {
        binding.buttonPlayPause.setOnClickListener {
            viewModel.togglePlayPause()
        }
        
        binding.buttonPrevious.setOnClickListener {
            viewModel.previousTrack()
        }
        
        binding.buttonNext.setOnClickListener {
            viewModel.nextTrack()
        }
    }
    
    private fun observeViewModel() {
        viewModel.currentMedia.observe(viewLifecycleOwner) { media ->
            if (media != null) {
                binding.textSongTitle.text = media.title
                binding.textArtist.text = media.artist
                binding.cardNowPlaying.visibility = View.VISIBLE
                
                // In a real implementation, this would load album art
                // For now, just use the media icon
                binding.imageAlbumArt.setImageResource(R.drawable.ic_media)
            } else {
                binding.cardNowPlaying.visibility = View.GONE
            }
        }
        
        viewModel.playbackState.observe(viewLifecycleOwner) { state ->
            when (state) {
                PlaybackState.PLAYING -> {
                    binding.buttonPlayPause.setImageResource(R.drawable.ic_pause)
                }
                else -> {
                    binding.buttonPlayPause.setImageResource(R.drawable.ic_play)
                }
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}