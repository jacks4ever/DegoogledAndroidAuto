package com.degoogled.androidauto.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.degoogled.androidauto.databinding.ItemMainMenuBinding

/**
 * Adapter for the main menu items.
 */
class MainMenuAdapter(
    private val onItemClick: (MenuItem) -> Unit
) : ListAdapter<MenuItem, MainMenuAdapter.MenuViewHolder>(MenuDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemMainMenuBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MenuViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class MenuViewHolder(
        private val binding: ItemMainMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }
        }
        
        fun bind(item: MenuItem) {
            binding.textMenuTitle.text = item.title
            binding.textMenuDescription.text = item.description
            binding.imageMenuIcon.setImageResource(item.iconResId)
        }
    }
    
    private class MenuDiffCallback : DiffUtil.ItemCallback<MenuItem>() {
        override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem == newItem
        }
    }
}