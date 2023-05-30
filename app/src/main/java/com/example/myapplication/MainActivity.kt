package com.example.myapplication

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private val musicUrl =
        "https://bazoka-system-app.s3.ap-southeast-1.amazonaws.com/Vizik/Music/Audio/Happy/happy-birthday-acoustic-spot-16196.mp3"
    private var isPlay: Boolean = false
    private lateinit var playButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playButton = findViewById<Button>(R.id.playButton)
        playButton.setOnClickListener {
            if (!isPlay) {
                playMusic()
            } else {
                stopMusic()
            }
        }
    }

    private fun playMusic() {
        playButton.text = "Pause"
        isPlay = true
        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
        )
        mediaPlayer.setDataSource(musicUrl)
        mediaPlayer.prepareAsync()
        mediaPlayer.setOnPreparedListener {
            mediaPlayer.start()
        }
        mediaPlayer.setOnCompletionListener {
            playButton.text = "Play"
            isPlay = false
        }
    }

    private fun stopMusic() {
        mediaPlayer.stop()
        mediaPlayer.reset()
        playButton.text = "Play"
        isPlay = false

    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}