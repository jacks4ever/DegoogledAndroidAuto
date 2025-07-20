package com.degoogled.minimalandroidauto.home

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import com.degoogled.minimalandroidauto.utils.PackageUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Integration with August Lock app
 */
class AugustLockIntegration(private val context: Context) {

    companion object {
        private const val TAG = "AugustLockIntegration"
        private const val AUGUST_PACKAGE = "com.august.luna"
        private const val AUGUST_PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=com.august.luna"
        private const val AUGUST_AURORA_URL = "market://details?id=com.august.luna"
        
        // Alternative lock apps
        private const val YALE_ACCESS_PACKAGE = "com.august.yale"
        
        // Deep link schemes
        private const val AUGUST_SCHEME = "august://"
        
        // Lock actions
        private const val ACTION_LOCK = "lock"
        private const val ACTION_UNLOCK = "unlock"
        private const val ACTION_VIEW_LOCK = "viewLock"
        private const val ACTION_VIEW_ACTIVITY = "viewActivity"
    }

    /**
     * Check if August Lock app is installed (either August or Yale Access)
     */
    fun isAugustInstalled(): Boolean {
        return PackageUtils.isPackageInstalled(context, AUGUST_PACKAGE) ||
               PackageUtils.isPackageInstalled(context, YALE_ACCESS_PACKAGE)
    }
    
    /**
     * Get the installed lock app package name
     */
    private fun getInstalledLockPackage(): String? {
        return when {
            PackageUtils.isPackageInstalled(context, AUGUST_PACKAGE) -> AUGUST_PACKAGE
            PackageUtils.isPackageInstalled(context, YALE_ACCESS_PACKAGE) -> YALE_ACCESS_PACKAGE
            else -> null
        }
    }

    /**
     * Get the intent to install August Lock app (tries Aurora Store first, then Play Store)
     */
    fun getAugustInstallIntent(): Intent {
        // Check if Aurora Store is installed
        if (PackageUtils.isPackageInstalled(context, "com.aurora.store")) {
            // If Aurora Store is installed, open it to the August page
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setPackage("com.aurora.store")
            intent.data = Uri.parse(AUGUST_AURORA_URL)
            return intent
        } else {
            // Fallback to Play Store or direct download
            return Intent(Intent.ACTION_VIEW, Uri.parse(AUGUST_PLAY_STORE_URL))
        }
    }

    /**
     * Launch August Lock app
     */
    suspend fun launchAugust() = withContext(Dispatchers.IO) {
        try {
            val packageName = getInstalledLockPackage()
            if (packageName != null) {
                val intent = context.packageManager.getLaunchIntentForPackage(packageName)
                if (intent != null) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                    Log.d(TAG, "Launched lock app: $packageName")
                } else {
                    Log.e(TAG, "Could not create launch intent for lock app: $packageName")
                }
            } else {
                Log.e(TAG, "No lock app installed")
                // Show installation prompt
                val installIntent = getAugustInstallIntent()
                installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(installIntent)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error launching lock app", e)
        }
    }

    /**
     * Lock a specific August Lock
     * @param lockId Lock ID (if null, will attempt to lock the default lock)
     */
    suspend fun lockDoor(lockId: String? = null) = withContext(Dispatchers.IO) {
        try {
            val uri = if (lockId != null) {
                "${AUGUST_SCHEME}$ACTION_LOCK?lockId=$lockId"
            } else {
                "${AUGUST_SCHEME}$ACTION_LOCK"
            }
            
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(uri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Error locking door", e)
            // Fallback to launching the app
            launchAugust()
        }
    }

    /**
     * Unlock a specific August Lock
     * @param lockId Lock ID (if null, will attempt to unlock the default lock)
     */
    suspend fun unlockDoor(lockId: String? = null) = withContext(Dispatchers.IO) {
        try {
            val uri = if (lockId != null) {
                "${AUGUST_SCHEME}$ACTION_UNLOCK?lockId=$lockId"
            } else {
                "${AUGUST_SCHEME}$ACTION_UNLOCK"
            }
            
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(uri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Error unlocking door", e)
            // Fallback to launching the app
            launchAugust()
        }
    }

    /**
     * View a specific lock's details
     * @param lockId Lock ID (if null, will attempt to view the default lock)
     */
    suspend fun viewLock(lockId: String? = null) = withContext(Dispatchers.IO) {
        try {
            val uri = if (lockId != null) {
                "${AUGUST_SCHEME}$ACTION_VIEW_LOCK?lockId=$lockId"
            } else {
                "${AUGUST_SCHEME}$ACTION_VIEW_LOCK"
            }
            
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(uri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Error viewing lock", e)
            // Fallback to launching the app
            launchAugust()
        }
    }

    /**
     * View activity log for a specific lock
     * @param lockId Lock ID (if null, will attempt to view activity for the default lock)
     */
    suspend fun viewActivity(lockId: String? = null) = withContext(Dispatchers.IO) {
        try {
            val uri = if (lockId != null) {
                "${AUGUST_SCHEME}$ACTION_VIEW_ACTIVITY?lockId=$lockId"
            } else {
                "${AUGUST_SCHEME}$ACTION_VIEW_ACTIVITY"
            }
            
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(uri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Error viewing activity", e)
            // Fallback to launching the app
            launchAugust()
        }
    }
}