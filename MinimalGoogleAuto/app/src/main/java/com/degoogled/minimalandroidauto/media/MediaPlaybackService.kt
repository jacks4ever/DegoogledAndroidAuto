package com.degoogled.minimalandroidauto.media

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.degoogled.minimalandroidauto.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player

/**
 * Service for media playback using ExoPlayer
 */
class MediaPlaybackService : Service() {

    companion object {
        private const val TAG = "MediaPlaybackService"
        private const val NOTIFICATION_ID = 1002
        private const val CHANNEL_ID = "media_playback_channel"
    }

    private var player: ExoPlayer? = null
    private var currentMediaItem: MediaItem? = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        initializePlayer()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_PLAY -> {
                    val mediaUri = it.getStringExtra(EXTRA_MEDIA_URI)
                    mediaUri?.let { uri ->
                        playMedia(uri)
                    }
                }
                ACTION_PAUSE -> pauseMedia()
                ACTION_RESUME -> resumeMedia()
                ACTION_STOP -> stopMedia()
                ACTION_NEXT -> nextTrack()
                ACTION_PREVIOUS -> previousTrack()
            }
        }
        
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        releasePlayer()
        super.onDestroy()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.notification_channel_media)
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(title: String = "Unknown", artist: String = "Unknown"): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(artist)
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(this).build()
        player?.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                when (state) {
                    Player.STATE_READY -> {
                        Log.d(TAG, "Player ready")
                    }
                    Player.STATE_ENDED -> {
                        Log.d(TAG, "Playback ended")
                    }
                    Player.STATE_BUFFERING -> {
                        Log.d(TAG, "Buffering")
                    }
                    Player.STATE_IDLE -> {
                        Log.d(TAG, "Player idle")
                    }
                }
            }
        })
    }

    private fun releasePlayer() {
        player?.release()
        player = null
    }

    private fun playMedia(mediaUri: String) {
        try {
            currentMediaItem = MediaItem.fromUri(mediaUri)
            player?.setMediaItem(currentMediaItem!!)
            player?.prepare()
            player?.play()
            
            // Start foreground service with notification
            startForeground(NOTIFICATION_ID, createNotification())
            
            Log.d(TAG, "Playing media: $mediaUri")
        } catch (e: Exception) {
            Log.e(TAG, "Error playing media", e)
        }
    }

    private fun pauseMedia() {
        player?.pause()
        Log.d(TAG, "Media paused")
    }

    private fun resumeMedia() {
        player?.play()
        Log.d(TAG, "Media resumed")
    }

    private fun stopMedia() {
        player?.stop()
        stopForeground(true)
        Log.d(TAG, "Media stopped")
    }

    private fun nextTrack() {
        player?.seekToNext()
        Log.d(TAG, "Next track")
    }

    private fun previousTrack() {
        player?.seekToPrevious()
        Log.d(TAG, "Previous track")
    }

    companion object {
        const val ACTION_PLAY = "com.degoogled.minimalandroidauto.action.PLAY"
        const val ACTION_PAUSE = "com.degoogled.minimalandroidauto.action.PAUSE"
        const val ACTION_RESUME = "com.degoogled.minimalandroidauto.action.RESUME"
        const val ACTION_STOP = "com.degoogled.minimalandroidauto.action.STOP"
        const val ACTION_NEXT = "com.degoogled.minimalandroidauto.action.NEXT"
        const val ACTION_PREVIOUS = "com.degoogled.minimalandroidauto.action.PREVIOUS"
        const val EXTRA_MEDIA_URI = "com.degoogled.minimalandroidauto.extra.MEDIA_URI"
    }
}