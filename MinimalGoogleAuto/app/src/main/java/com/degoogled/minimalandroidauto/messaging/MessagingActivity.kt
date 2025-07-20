package com.degoogled.minimalandroidauto.messaging

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.degoogled.minimalandroidauto.R
import com.degoogled.minimalandroidauto.databinding.ActivityMessagingBinding
import com.degoogled.minimalandroidauto.utils.PrivacyPreferences
import kotlinx.coroutines.launch

class MessagingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessagingBinding
    private lateinit var whatsAppIntegration: WhatsAppIntegration
    private lateinit var teamsIntegration: TeamsIntegration
    private lateinit var handcentIntegration: HandcentIntegration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize integrations
        whatsAppIntegration = WhatsAppIntegration(this)
        teamsIntegration = TeamsIntegration(this)
        handcentIntegration = HandcentIntegration(this)

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
        // WhatsApp privacy switch
        binding.switchWhatsAppPrivacy.isChecked = PrivacyPreferences.isWhatsAppPrivacyEnabled(this)
        binding.switchWhatsAppPrivacy.setOnCheckedChangeListener { _, isChecked ->
            PrivacyPreferences.setWhatsAppPrivacyEnabled(this, isChecked)
            updatePrivacyStatus("WhatsApp", isChecked)
        }

        // Teams privacy switch
        binding.switchTeamsPrivacy.isChecked = PrivacyPreferences.isTeamsPrivacyEnabled(this)
        binding.switchTeamsPrivacy.setOnCheckedChangeListener { _, isChecked ->
            PrivacyPreferences.setTeamsPrivacyEnabled(this, isChecked)
            updatePrivacyStatus("Teams", isChecked)
        }

        // Handcent privacy switch
        binding.switchHandcentPrivacy.isChecked = PrivacyPreferences.isHandcentPrivacyEnabled(this)
        binding.switchHandcentPrivacy.setOnCheckedChangeListener { _, isChecked ->
            PrivacyPreferences.setHandcentPrivacyEnabled(this, isChecked)
            updatePrivacyStatus("Handcent", isChecked)
        }
    }

    private fun setupAppButtons() {
        // WhatsApp button
        binding.buttonWhatsApp.setOnClickListener {
            if (whatsAppIntegration.isWhatsAppInstalled()) {
                lifecycleScope.launch {
                    whatsAppIntegration.launchWhatsApp()
                }
            } else {
                startActivity(whatsAppIntegration.getWhatsAppInstallIntent())
            }
        }

        // Teams button
        binding.buttonTeams.setOnClickListener {
            if (teamsIntegration.isTeamsInstalled()) {
                lifecycleScope.launch {
                    teamsIntegration.launchTeams()
                }
            } else {
                startActivity(teamsIntegration.getTeamsInstallIntent())
            }
        }

        // Handcent button
        binding.buttonHandcent.setOnClickListener {
            if (handcentIntegration.isHandcentInstalled()) {
                lifecycleScope.launch {
                    handcentIntegration.launchHandcent()
                }
            } else {
                startActivity(handcentIntegration.getHandcentInstallIntent())
            }
        }
    }

    private fun updateAppStatus() {
        // Update WhatsApp status
        if (whatsAppIntegration.isWhatsAppInstalled()) {
            binding.textWhatsAppStatus.text = getString(R.string.installed)
            binding.textWhatsAppStatus.setTextColor(getColor(R.color.success))
            binding.buttonWhatsApp.text = getString(R.string.launch_whatsapp)
        } else {
            binding.textWhatsAppStatus.text = getString(R.string.not_installed)
            binding.textWhatsAppStatus.setTextColor(getColor(R.color.error))
            binding.buttonWhatsApp.text = getString(R.string.install_whatsapp)
        }

        // Update Teams status
        if (teamsIntegration.isTeamsInstalled()) {
            binding.textTeamsStatus.text = getString(R.string.installed)
            binding.textTeamsStatus.setTextColor(getColor(R.color.success))
            binding.buttonTeams.text = getString(R.string.launch_teams)
        } else {
            binding.textTeamsStatus.text = getString(R.string.not_installed)
            binding.textTeamsStatus.setTextColor(getColor(R.color.error))
            binding.buttonTeams.text = getString(R.string.install_teams)
        }

        // Update Handcent status
        if (handcentIntegration.isHandcentInstalled()) {
            binding.textHandcentStatus.text = getString(R.string.installed)
            binding.textHandcentStatus.setTextColor(getColor(R.color.success))
            binding.buttonHandcent.text = getString(R.string.launch_handcent)
        } else {
            binding.textHandcentStatus.text = getString(R.string.not_installed)
            binding.textHandcentStatus.setTextColor(getColor(R.color.error))
            binding.buttonHandcent.text = getString(R.string.install_handcent)
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