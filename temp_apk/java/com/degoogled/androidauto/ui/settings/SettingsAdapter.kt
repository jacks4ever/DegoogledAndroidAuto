package com.degoogled.androidauto.ui.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.degoogled.androidauto.data.model.Setting
import com.degoogled.androidauto.databinding.ItemSettingBinding

/**
 * Adapter for the settings list.
 */
class SettingsAdapter(
    private val onToggleClick: (Setting, Boolean) -> Unit,
    private val onItemClick: (Setting) -> Unit
) : ListAdapter<Setting, SettingsAdapter.SettingViewHolder>(SettingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        val binding = ItemSettingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SettingViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class SettingViewHolder(
        private val binding: ItemSettingBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val setting = getItem(position)
                    onItemClick(setting)
                }
            }
            
            binding.switchSetting.setOnCheckedChangeListener { _, isChecked ->
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val setting = getItem(position)
                    onToggleClick(setting, isChecked)
                }
            }
        }
        
        fun bind(setting: Setting) {
            binding.textSettingTitle.text = setting.name
            binding.textSettingDescription.text = setting.description
            binding.imageSettingIcon.setImageResource(setting.iconResId)
            
            if (setting.isToggle) {
                binding.switchSetting.visibility = View.VISIBLE
                binding.switchSetting.isChecked = setting.value as Boolean
            } else {
                binding.switchSetting.visibility = View.GONE
            }
        }
    }
    
    private class SettingDiffCallback : DiffUtil.ItemCallback<Setting>() {
        override fun areItemsTheSame(oldItem: Setting, newItem: Setting): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Setting, newItem: Setting): Boolean {
            return oldItem == newItem
        }
    }
}