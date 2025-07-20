package com.degoogled.minimalandroidauto.network

import android.content.Context
import android.util.Log
import com.degoogled.minimalandroidauto.utils.PrivacyPreferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.util.concurrent.TimeUnit

/**
 * Network blocker to intercept and filter Google analytics and tracking requests
 */
class NetworkBlocker private constructor(private val context: Context) {

    companion object {
        private const val TAG = "NetworkBlocker"
        private var instance: NetworkBlocker? = null

        // List of domains to block
        private val BLOCKED_DOMAINS = listOf(
            "google-analytics.com",
            "analytics.google.com",
            "doubleclick.net",
            "googleadservices.com",
            "googletagmanager.com",
            "crashlytics.com",
            "firebase-settings.crashlytics.com",
            "app-measurement.com",
            "firebaselogging-pa.googleapis.com",
            "firebaseinstallations.googleapis.com",
            "fcmtoken.googleapis.com",
            "firebaseremoteconfig.googleapis.com"
        )

        // List of allowed Google domains for Android Auto functionality
        private val ALLOWED_DOMAINS = listOf(
            "gstatic.com",
            "googleapis.com"
        )

        // List of allowed paths for minimal Google authentication
        private val ALLOWED_PATHS = listOf(
            "/auth/",
            "/token/"
        )

        fun init(context: Context) {
            if (instance == null) {
                instance = NetworkBlocker(context.applicationContext)
            }
        }

        fun getInstance(context: Context): NetworkBlocker {
            if (instance == null) {
                init(context)
            }
            return instance!!
        }
    }

    /**
     * Create an OkHttpClient with network blocking interceptor
     */
    fun createBlockingOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createNetworkInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Create a network interceptor to block unwanted requests
     */
    fun createNetworkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val url = request.url.toString()
            val host = request.url.host

            // Check if privacy mode is enabled
            if (PrivacyPreferences.isPrivacyModeEnabled(context)) {
                // Block analytics domains
                if (PrivacyPreferences.isAnalyticsBlockingEnabled(context) && 
                    BLOCKED_DOMAINS.any { host.contains(it) }) {
                    Log.d(TAG, "Blocking request to: $url")
                    return@Interceptor createEmptyResponse(chain)
                }

                // Check for Google domains
                if (ALLOWED_DOMAINS.any { host.contains(it) }) {
                    // Only allow minimal authentication if enabled
                    if (PrivacyPreferences.isMinimalAuthEnabled(context)) {
                        val path = request.url.encodedPath
                        if (ALLOWED_PATHS.none { path.contains(it) }) {
                            Log.d(TAG, "Blocking non-auth Google request: $url")
                            return@Interceptor createEmptyResponse(chain)
                        }
                    }
                }

                // Block all network in offline mode
                if (PrivacyPreferences.isOfflineModeEnabled(context)) {
                    Log.d(TAG, "Blocking request in offline mode: $url")
                    return@Interceptor createEmptyResponse(chain)
                }
            }

            // Log the request if network monitoring is enabled
            if (PrivacyPreferences.isNetworkMonitoringEnabled(context)) {
                Log.d(TAG, "Network request: ${request.method} $url")
            }

            // Proceed with the request
            chain.proceed(request)
        }
    }

    /**
     * Create an empty response for blocked requests
     */
    private fun createEmptyResponse(chain: Interceptor.Chain): Response {
        return Response.Builder()
            .request(chain.request())
            .protocol(okhttp3.Protocol.HTTP_1_1)
            .code(204)
            .message("No Content")
            .body(okhttp3.ResponseBody.create(null, ByteArray(0)))
            .build()
    }
}