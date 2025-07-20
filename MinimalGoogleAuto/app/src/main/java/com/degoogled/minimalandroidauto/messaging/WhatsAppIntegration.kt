package com.degoogled.minimalandroidauto.messaging

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Integration with WhatsApp messaging app
 */
class WhatsAppIntegration(private val context: Context) {

    companion object {
        private const val TAG = "WhatsAppIntegration"
        private const val WHATSAPP_PACKAGE = "com.whatsapp"
        private const val WHATSAPP_PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=com.whatsapp"
    }

    /**
     * Check if WhatsApp is installed
     */
    fun isWhatsAppInstalled(): Boolean {
        return try {
            context.packageManager.getPackageInfo(WHATSAPP_PACKAGE, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    /**
     * Get the Play Store intent to install WhatsApp
     */
    fun getWhatsAppInstallIntent(): Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse(WHATSAPP_PLAY_STORE_URL))
    }

    /**
     * Launch WhatsApp app
     */
    suspend fun launchWhatsApp() = withContext(Dispatchers.IO) {
        try {
            val intent = context.packageManager.getLaunchIntentForPackage(WHATSAPP_PACKAGE)
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            } else {
                Log.e(TAG, "Could not create launch intent for WhatsApp")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error launching WhatsApp", e)
        }
    }

    /**
     * Open chat with a specific phone number
     * @param phoneNumber Phone number with country code but without + or 00 (e.g., "1234567890")
     * @param message Optional message to pre-fill
     */
    suspend fun openChat(phoneNumber: String, message: String? = null) = withContext(Dispatchers.IO) {
        try {
            // Format: https://api.whatsapp.com/send?phone=PHONE_NUMBER&text=MESSAGE
            var uri = "https://api.whatsapp.com/send?phone=$phoneNumber"
            if (!message.isNullOrEmpty()) {
                uri += "&text=${Uri.encode(message)}"
            }
            
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(uri)
            intent.setPackage(WHATSAPP_PACKAGE)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Error opening WhatsApp chat", e)
            // Fallback to launching the app
            launchWhatsApp()
        }
    }

    /**
     * Share text via WhatsApp
     */
    suspend fun shareText(text: String) = withContext(Dispatchers.IO) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, text)
            intent.setPackage(WHATSAPP_PACKAGE)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Error sharing text via WhatsApp", e)
            // Fallback to launching the app
            launchWhatsApp()
        }
    }

    /**
     * Open WhatsApp with a specific contact
     * @param contactId Contact ID from WhatsApp's database
     */
    suspend fun openContact(contactId: String) = withContext(Dispatchers.IO) {
        try {
            val uri = Uri.parse("content://com.whatsapp.provider.contact/contacts/$contactId")
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(uri, "vnd.android.cursor.item/vnd.com.whatsapp.voip.call")
            intent.setPackage(WHATSAPP_PACKAGE)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Error opening WhatsApp contact", e)
            // Fallback to launching the app
            launchWhatsApp()
        }
    }
}