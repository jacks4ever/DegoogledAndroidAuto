package com.degoogled.minimalandroidauto.messaging

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import com.degoogled.minimalandroidauto.utils.PackageUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Integration with Handcent SMS app
 */
class HandcentIntegration(private val context: Context) {

    companion object {
        private const val TAG = "HandcentIntegration"
        private const val HANDCENT_PACKAGE = "com.handcent.nextsms"
        private const val HANDCENT_PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=com.handcent.nextsms"
        private const val HANDCENT_AURORA_URL = "market://details?id=com.handcent.nextsms"
        
        // Alternative SMS apps that might be installed
        private const val SIGNAL_PACKAGE = "org.thoughtcrime.securesms"
        private const val QKSMS_PACKAGE = "com.moez.QKSMS"
        private const val SILENCE_PACKAGE = "org.smssecure.smssecure"
        
        // Intent actions
        private const val ACTION_VIEW_CONVERSATIONS = "com.handcent.nextsms.CONVERSATIONS"
        private const val ACTION_VIEW_CONVERSATION = "com.handcent.nextsms.CONVERSATION"
        private const val ACTION_COMPOSE = "com.handcent.nextsms.COMPOSE"
    }

    /**
     * Check if Handcent is installed
     */
    fun isHandcentInstalled(): Boolean {
        return PackageUtils.isPackageInstalled(context, HANDCENT_PACKAGE)
    }
    
    /**
     * Check if any supported SMS app is installed
     */
    fun isAnySmsAppInstalled(): Boolean {
        return PackageUtils.isPackageInstalled(context, HANDCENT_PACKAGE) ||
               PackageUtils.isPackageInstalled(context, SIGNAL_PACKAGE) ||
               PackageUtils.isPackageInstalled(context, QKSMS_PACKAGE) ||
               PackageUtils.isPackageInstalled(context, SILENCE_PACKAGE)
    }
    
    /**
     * Get the package name of the installed SMS app
     */
    private fun getInstalledSmsPackage(): String? {
        return when {
            PackageUtils.isPackageInstalled(context, HANDCENT_PACKAGE) -> HANDCENT_PACKAGE
            PackageUtils.isPackageInstalled(context, SIGNAL_PACKAGE) -> SIGNAL_PACKAGE
            PackageUtils.isPackageInstalled(context, QKSMS_PACKAGE) -> QKSMS_PACKAGE
            PackageUtils.isPackageInstalled(context, SILENCE_PACKAGE) -> SILENCE_PACKAGE
            else -> null
        }
    }

    /**
     * Get the intent to install Handcent (tries Aurora Store first, then Play Store)
     */
    fun getHandcentInstallIntent(): Intent {
        // Check if Aurora Store is installed
        if (PackageUtils.isPackageInstalled(context, "com.aurora.store")) {
            // If Aurora Store is installed, open it to the Handcent page
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setPackage("com.aurora.store")
            intent.data = Uri.parse(HANDCENT_AURORA_URL)
            return intent
        } else {
            // Fallback to Play Store or direct download
            return Intent(Intent.ACTION_VIEW, Uri.parse(HANDCENT_PLAY_STORE_URL))
        }
    }

    /**
     * Launch SMS app (Handcent preferred, but falls back to other SMS apps)
     */
    suspend fun launchHandcent() = withContext(Dispatchers.IO) {
        try {
            val packageName = getInstalledSmsPackage()
            if (packageName != null) {
                val intent = context.packageManager.getLaunchIntentForPackage(packageName)
                if (intent != null) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                    Log.d(TAG, "Launched SMS app: $packageName")
                } else {
                    Log.e(TAG, "Could not create launch intent for SMS app: $packageName")
                }
            } else {
                Log.e(TAG, "No supported SMS app installed")
                // Show installation prompt for Handcent
                val installIntent = getHandcentInstallIntent()
                installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(installIntent)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error launching SMS app", e)
        }
    }

    /**
     * View all conversations
     */
    suspend fun viewConversations() = withContext(Dispatchers.IO) {
        try {
            val intent = Intent(ACTION_VIEW_CONVERSATIONS)
            intent.setPackage(HANDCENT_PACKAGE)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Error viewing conversations", e)
            // Fallback to launching the app
            launchHandcent()
        }
    }

    /**
     * View a specific conversation by thread ID
     */
    suspend fun viewConversation(threadId: String) = withContext(Dispatchers.IO) {
        try {
            val intent = Intent(ACTION_VIEW_CONVERSATION)
            intent.setPackage(HANDCENT_PACKAGE)
            intent.putExtra("thread_id", threadId)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Error viewing conversation", e)
            // Fallback to viewing all conversations
            viewConversations()
        }
    }

    /**
     * Compose a new message to a specific address
     */
    suspend fun composeMessage(address: String, message: String? = null) = withContext(Dispatchers.IO) {
        try {
            val intent = Intent(ACTION_COMPOSE)
            intent.setPackage(HANDCENT_PACKAGE)
            intent.putExtra("address", address)
            if (message != null) {
                intent.putExtra("sms_body", message)
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Error composing message", e)
            
            // Fallback to using standard SMS intent
            try {
                val uri = Uri.parse("smsto:$address")
                val intent = Intent(Intent.ACTION_SENDTO, uri)
                if (message != null) {
                    intent.putExtra("sms_body", message)
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            } catch (e2: Exception) {
                Log.e(TAG, "Error with fallback SMS intent", e2)
            }
        }
    }
}