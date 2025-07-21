package com.degoogled.minimalandroidauto.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.degoogled.minimalandroidauto.R
import com.degoogled.minimalandroidauto.databinding.ActivityHomeBinding
import com.degoogled.minimalandroidauto.utils.PrivacyPreferences
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var augustLockIntegration: AugustLockIntegration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize integrations
        augustLockIntegration = AugustLockIntegration(this)

        // Set up privacy switches
        setupPrivacySwitches()

        // Set up app buttons
        setupAppButtons()

        // Set up back button
        binding.buttonBack.setOnClickListener {
            finish()
        }

        // Update app status
        updateAppStatus()
    }

    override fun onResume() {
        super.onResume()
        updateAppStatus()
    }

    private fun setupPrivacySwitches() {
        // August privacy switch
        binding.switchAugustPrivacy.isChecked = PrivacyPreferences.isAugustPrivacyEnabled(this)
        binding.switchAugustPrivacy.setOnCheckedChangeListener { _, isChecked ->
            PrivacyPreferences.setAugustPrivacyEnabled(this, isChecked)
            updatePrivacyStatus("August Lock", isChecked)
        }
    }

    private fun setupAppButtons() {
        // August Launch button
        binding.buttonAugustLaunch.setOnClickListener {
            if (augustLockIntegration.isAugustInstalled()) {
                lifecycleScope.launch {
                    augustLockIntegration.launchAugust()
                }
            } else {
                startActivity(augustLockIntegration.getAugustInstallIntent())
            }
        }

        // Lock button
        binding.buttonLock.setOnClickListener {
            if (augustLockIntegration.isAugustInstalled()) {
                lifecycleScope.launch {
                    augustLockIntegration.lockDoor()
                    Toast.makeText(this@HomeActivity, "Locking door...", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "August Lock not installed", Toast.LENGTH_SHORT).show()
            }
        }

        // Unlock button
        binding.buttonUnlock.setOnClickListener {
            if (augustLockIntegration.isAugustInstalled()) {
                lifecycleScope.launch {
                    augustLockIntegration.unlockDoor()
                    Toast.makeText(this@HomeActivity, "Unlocking door...", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "August Lock not installed", Toast.LENGTH_SHORT).show()
            }
        }

        // View Activity button
        binding.buttonViewActivity.setOnClickListener {
            if (augustLockIntegration.isAugustInstalled()) {
                lifecycleScope.launch {
                    augustLockIntegration.viewActivity()
                }
            } else {
                Toast.makeText(this, "August Lock not installed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateAppStatus() {
        // Update August status
        if (augustLockIntegration.isAugustInstalled()) {
            binding.textAugustStatus.text = getString(R.string.installed)
            binding.textAugustStatus.setTextColor(getColor(R.color.success))
            binding.buttonAugustLaunch.text = getString(R.string.launch_august)
            binding.buttonLock.isEnabled = true
            binding.buttonUnlock.isEnabled = true
            binding.buttonViewActivity.isEnabled = true
        } else {
            binding.textAugustStatus.text = getString(R.string.not_installed)
            binding.textAugustStatus.setTextColor(getColor(R.color.error))
            binding.buttonAugustLaunch.text = getString(R.string.install_august)
            binding.buttonLock.isEnabled = false
            binding.buttonUnlock.isEnabled = false
            binding.buttonViewActivity.isEnabled = false
        }
    }

    private fun updatePrivacyStatus(app: String, isEnabled: Boolean) {
        val message = if (isEnabled) {
            "$app privacy protection enabled"
        } else {
            "$app privacy protection disabled"
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}