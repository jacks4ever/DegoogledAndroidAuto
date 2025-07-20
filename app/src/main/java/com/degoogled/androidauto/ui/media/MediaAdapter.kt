package com.degoogled.androidauto.ui.media

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.degoogled.androidauto.R
import com.degoogled.androidauto.data.model.Media
import com.degoogled.androidauto.databinding.ItemMediaBinding

/**
 * Adapter for the media list.
 */
class MediaAdapter(
    private val onItemClick: (Media) -> Unit
) : ListAdapter<Media, MediaAdapter.MediaViewHolder>(MediaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val binding = ItemMediaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MediaViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class MediaViewHolder(
        private val binding: ItemMediaBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }
        }
        
        fun bind(media: Media) {
            binding.textMediaTitle.text = media.title
            binding.textMediaArtist.text = media.artist
            
            // In a real implementation, this would load album art
            // For now, just use the media icon
            binding.imageMediaArt.setImageResource(R.drawable.ic_media)
        }
    }
    
    private class MediaDiffCallback : DiffUtil.ItemCallback<Media>() {
        override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
            return oldItem == newItem
        }
    }
}