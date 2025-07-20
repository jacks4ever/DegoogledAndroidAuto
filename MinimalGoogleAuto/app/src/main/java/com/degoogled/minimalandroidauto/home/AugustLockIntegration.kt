package com.degoogled.minimalandroidauto.home

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
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
        
        // Deep link schemes
        private const val AUGUST_SCHEME = "august://"
        
        // Lock actions
        private const val ACTION_LOCK = "lock"
        private const val ACTION_UNLOCK = "unlock"
        private const val ACTION_VIEW_LOCK = "viewLock"
        private const val ACTION_VIEW_ACTIVITY = "viewActivity"
    }

    /**
     * Check if August Lock app is installed
     */
    fun isAugustInstalled(): Boolean {
        return try {
            context.packageManager.getPackageInfo(AUGUST_PACKAGE, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    /**
     * Get the Play Store intent to install August Lock app
     */
    fun getAugustInstallIntent(): Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse(AUGUST_PLAY_STORE_URL))
    }

    /**
     * Launch August Lock app
     */
    suspend fun launchAugust() = withContext(Dispatchers.IO) {
        try {
            val intent = context.packageManager.getLaunchIntentForPackage(AUGUST_PACKAGE)
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            } else {
                Log.e(TAG, "Could not create launch intent for August Lock")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error launching August Lock", e)
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