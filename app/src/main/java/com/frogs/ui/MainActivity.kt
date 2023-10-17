package com.frogs.ui

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.frogs.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val audioActive = getSharedPreferences(PREF_MUSIC_NAME, Context.MODE_PRIVATE).getBoolean(
            PREF_MUSIC_VALUE, true
        )
        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer.isLooping = true

        if (audioActive){
            mediaPlayer.start()
        }

    }

    override fun onStop() {
        mediaPlayer.stop()
        super.onStop()
    }

    fun stopOrPlay(value: Boolean) {
        if (value) {
            mediaPlayer.start()
        } else {
            mediaPlayer.pause()
        }

    }

}
