package com.frogs.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.frogs.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var audioCheckBox: LottieAnimationView
    private lateinit var musicCheckBox: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        val prefMusic =
            requireActivity().getSharedPreferences(PREF_MUSIC_NAME, Context.MODE_PRIVATE)



        with(binding) {
            audioCheckBox = animAudio
            musicCheckBox = animMusic
            val activity = requireActivity() as MainActivity
            if (prefMusic.getBoolean(PREF_AUDIO_VALUE, true)) {
                audioCheckBox.apply {
                    progress = 1f

                }
            } else {
                audioCheckBox.apply {
                    progress = 0.15f

                }
            }

            if (prefMusic.getBoolean(PREF_MUSIC_VALUE, true)) {
                musicCheckBox.apply {
                    progress = 1f

                }
            } else {
                musicCheckBox.apply {
                    progress = 0.15f
                }
            }

            audioCheckBox.setOnClickListener {
                if (prefMusic.getBoolean(PREF_AUDIO_VALUE, true)) {
                    audioCheckBox.apply {
                        progress = 0.15f
                    }
                    prefMusic.edit()
                        .putBoolean(PREF_AUDIO_VALUE, false)
                        .apply()
                } else {
                    audioCheckBox.apply {
                        setMinProgress(0.15F)
                        setMaxProgress(1F)
                        repeatMode = LottieDrawable.REVERSE
                        playAnimation()
                    }
                    prefMusic.edit()
                        .putBoolean(PREF_AUDIO_VALUE, true)
                        .apply()
                }
            }

            musicCheckBox.setOnClickListener {
                if (prefMusic.getBoolean(PREF_MUSIC_VALUE, true)) {
                    musicCheckBox.apply {
                        progress = 0.15f
                    }
                    prefMusic.edit()
                        .putBoolean(PREF_MUSIC_VALUE, false)
                        .apply()
                    activity.stopOrPlay(false)
                } else {
                    musicCheckBox.apply {
                        setMinProgress(0.15F)
                        setMaxProgress(1F)
                        repeatMode = LottieDrawable.REVERSE
                        playAnimation()
                    }
                    prefMusic.edit()
                        .putBoolean(PREF_MUSIC_VALUE, true)
                        .apply()
                    activity.stopOrPlay(true)

                }

            }

        }




        return binding.root
    }


}

const val PREF_MUSIC_NAME = "PREF_MUSIC_NAME"

const val PREF_MUSIC_VALUE = "PREF_MUSIC_VALUE"
const val PREF_AUDIO_VALUE = "PREF_AUDIO_VALUE"

