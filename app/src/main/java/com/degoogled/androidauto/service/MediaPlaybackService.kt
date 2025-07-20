package com.degoogled.androidauto.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.degoogled.androidauto.R
import com.degoogled.androidauto.data.model.Media
import com.degoogled.androidauto.data.repository.MediaRepository
import com.degoogled.androidauto.data.repository.PlaybackState
import com.degoogled.androidauto.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media as VlcMedia
import org.videolan.libvlc.MediaPlayer
import org.videolan.libvlc.util.VLCUtil
import java.io.File

/**
 * Service for media playback.
 * Uses libVLC for media playback.
 */
class MediaPlaybackService : Service() {

    private val mediaRepository: MediaRepository by inject()
    
    private val serviceScope = CoroutineScope(Dispatchers.Main + Job())
    
    private val binder = MediaPlaybackBinder()
    
    private var libVlc: LibVLC? = null
    private var mediaPlayer: MediaPlayer? = null
    
    private var currentMedia: Media? = null
    
    override fun onCreate() {
        super.onCreate()
        initVlc()
        observeMediaRepository()
    }
    
    private fun initVlc() {
        try {
            val options = ArrayList<String>().apply {
                add("--no-snapshot-preview")
                add("--audio-time-stretch")
                add("-vvv")
            }
            
            libVlc = LibVLC(this, options)
            mediaPlayer = MediaPlayer(libVlc)
            
            mediaPlayer?.setEventListener { event ->
                when (event.type) {
                    MediaPlayer.Event.EndReached -> {
                        serviceScope.launch {
                            mediaRepository.nextTrack()
                        }
                    }
                    MediaPlayer.Event.Playing -> {
                        serviceScope.launch {
                            mediaRepository.setPlaybackState(PlaybackState.PLAYING)
                        }
                    }
                    MediaPlayer.Event.Paused -> {
                        serviceScope.launch {
                            mediaRepository.setPlaybackState(PlaybackState.PAUSED)
                        }
                    }
                    MediaPlayer.Event.Stopped -> {
                        serviceScope.launch {
                            mediaRepository.setPlaybackState(PlaybackState.STOPPED)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing VLC", e)
        }
    }
    
    private fun observeMediaRepository() {
        serviceScope.launch {
            mediaRepository.getCurrentMedia().collectLatest { media ->
                if (media != null && media != currentMedia) {
                    currentMedia = media
                    playMedia(media)
                }
            }
        }
        
        serviceScope.launch {
            mediaRepository.getPlaybackState().collectLatest { state ->
                when (state) {
                    PlaybackState.PLAYING -> {
                        if (mediaPlayer?.isPlaying == false) {
                            mediaPlayer?.play()
                        }
                        updateNotification()
                    }
                    PlaybackState.PAUSED -> {
                        if (mediaPlayer?.isPlaying == true) {
                            mediaPlayer?.pause()
                        }
                        updateNotification()
                    }
                    PlaybackState.STOPPED -> {
                        mediaPlayer?.stop()
                        stopForeground(true)
                    }
                }
            }
        }
    }
    
    private fun playMedia(media: Media) {
        try {
            val vlcMedia = when {
                media.uri.startsWith("http") -> {
                    // Streaming URL
                    VlcMedia(libVlc, media.uri)
                }
                else -> {
                    // Local file
                    val file = File(media.uri)
                    VlcMedia(libVlc, file)
                }
            }
            
            mediaPlayer?.media = vlcMedia
            vlcMedia.release()
            
            mediaPlayer?.play()
            updateNotification()
        } catch (e: Exception) {
            Log.e(TAG, "Error playing media", e)
        }
    }
    
    private fun updateNotification() {
        val media = currentMedia ?: return
        
        val channelId = "media_playback_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Media Playback",
                NotificationManager.IMPORTANCE_LOW
            )
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle(media.title)
            .setContentText(media.artist)
            .setSmallIcon(R.drawable.ic_media)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()
        
        startForeground(NOTIFICATION_ID, notification)
    }
    
    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
    
    override fun onDestroy() {
        mediaPlayer?.release()
        libVlc?.release()
        super.onDestroy()
    }
    
    inner class MediaPlaybackBinder : Binder() {
        fun getService(): MediaPlaybackService = this@MediaPlaybackService
    }
    
    companion object {
        private const val TAG = "MediaPlaybackService"
        private const val NOTIFICATION_ID = 1
    }
}