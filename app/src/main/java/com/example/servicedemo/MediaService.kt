package com.example.servicedemo

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat

class MediaService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    private val FOREGROUND_ID = 7

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(FOREGROUND_ID, foregroundNotification())
        mediaPlayer = MediaPlayer.create(this, R.raw.test)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
        Toast.makeText(this, "Play music", Toast.LENGTH_SHORT).show()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        Toast.makeText(this, "Stop music", Toast.LENGTH_SHORT).show()
    }

    private fun foregroundNotification(): Notification {
        return NotificationCompat.Builder(this, "notification_channel_id")
            .setContentTitle("Media player service")
            .setContentText("Play music")
            .setTicker("Media player service ticker")
            .setSmallIcon(R.drawable.ic_baseline_music_note_24)
            .build()
    }
}