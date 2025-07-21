package com.degoogled.minimalandroidauto.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log

/**
 * Utility class for package management operations
 */
object PackageUtils {
    private const val TAG = "PackageUtils"

    /**
     * Check if a package is installed, regardless of installation source (Google Play, Aurora Store, etc.)
     *
     * @param context The application context
     * @param packageName The package name to check
     * @return True if the package is installed, false otherwise
     */
    fun isPackageInstalled(context: Context, packageName: String): Boolean {
        return try {
            // This will check if the package is installed regardless of the installation source
            context.packageManager.getPackageInfo(packageName, 0)
            Log.d(TAG, "Package $packageName is installed")
            true
        } catch (e: PackageManager.NameNotFoundException) {
            Log.d(TAG, "Package $packageName is not installed")
            false
        }
    }

    /**
     * Launch an app by package name
     *
     * @param context The application context
     * @param packageName The package name to launch
     * @return True if the app was launched successfully, false otherwise
     */
    fun launchApp(context: Context, packageName: String): Boolean {
        return try {
            if (isPackageInstalled(context, packageName)) {
                val launchIntent = context.packageManager.getLaunchIntentForPackage(packageName)
                if (launchIntent != null) {
                    launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(launchIntent)
                    Log.d(TAG, "Successfully launched $packageName")
                    true
                } else {
                    Log.e(TAG, "No launch intent found for $packageName")
                    false
                }
            } else {
                Log.e(TAG, "Cannot launch $packageName as it is not installed")
                false
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error launching $packageName", e)
            false
        }
    }
}