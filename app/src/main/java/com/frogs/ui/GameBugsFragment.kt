package com.frogs.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.frogs.R
import com.frogs.databinding.FragmentGameBugsBinding
import com.frogs.ui.viewModels.StarsViewModel
import com.frogs.ui.viewModels.ViewModelBugs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameBugsFragment : Fragment() {
    private lateinit var binding: FragmentGameBugsBinding
    private val bugsViewModel: ViewModelBugs by viewModels()
    private val starsViewModel: StarsViewModel by viewModels()
    private lateinit var frogImageView: ImageView
    private lateinit var timerText: TextView
    private lateinit var timerDown: CountDownTimer
    private var frogPositions = mutableListOf<Int>()
    private var countOfStars = 6
    private var time = 0L
    private lateinit var mediaPlayer: MediaPlayer



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBugsBinding.inflate(layoutInflater, container, false)
        val isAudioActive = requireActivity().getSharedPreferences(PREF_MUSIC_NAME , Context.MODE_PRIVATE)
            .getBoolean(PREF_AUDIO_VALUE , true)
        bugsViewModel.getBugs()
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.hrum)
        mediaPlayer.isLooping = false
        timerDown = object : CountDownTimer(countOfStars * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                time = millisUntilFinished
                updateTime()
            }

            override fun onFinish() {
                bugsViewModel.lose()
            }
        }
        timerDown.start()

        with(binding) {
            frogImageView = frog
            timerText = timer


            bug1.setOnClickListener {
                animateFrog(bug1 , isAudioActive)
                frogPositions
                checkWin(1)
            }
            bug2.setOnClickListener {
                animateFrog(bug2 , isAudioActive)
                checkWin(2)
            }
            bug3.setOnClickListener {
                animateFrog(bug3 , isAudioActive)
                checkWin(3)

            }
            bug4.setOnClickListener {
                animateFrog(bug4 , isAudioActive)
                checkWin(4)

            }
            bug5.setOnClickListener {
                animateFrog(bug5 , isAudioActive)
                checkWin(5)

            }
            bug6.setOnClickListener {
                animateFrog(bug6 , isAudioActive)
                checkWin(6)

            }
            bug7.setOnClickListener {
                animateFrog(bug7 , isAudioActive)
                checkWin(7)

            }
            bug8.setOnClickListener {
                animateFrog(bug8 , isAudioActive)
                checkWin(8)

            }
            bug9.setOnClickListener {
                animateFrog(bug9 , isAudioActive)
                checkWin(9)

            }

            buttonReply.setOnClickListener {
                restart()
            }

            buttonHome.setOnClickListener {
                findNavController().navigateUp()
            }




            bugsViewModel.countBugModelLiveData.observe(viewLifecycleOwner) {
                if (it.count != -1) {
                    countOfStars = it.count
                    frogPositions = it.frogPositions.toMutableList()
                    for (i in it.frogPositions) {
                        when (i) {
                            1 -> {
                                bug1.visibility = View.VISIBLE
                            }

                            2 -> {
                                bug2.visibility = View.VISIBLE

                            }

                            3 -> {
                                bug3.visibility = View.VISIBLE

                            }

                            4 -> {
                                bug4.visibility = View.VISIBLE

                            }

                            5 -> {
                                bug5.visibility = View.VISIBLE

                            }

                            6 -> {
                                bug6.visibility = View.VISIBLE

                            }

                            7 -> {
                                bug7.visibility = View.VISIBLE

                            }

                            8 -> {
                                bug8.visibility = View.VISIBLE

                            }

                            9 -> {
                                bug9.visibility = View.VISIBLE

                            }
                        }
                    }
                }
            }

            bugsViewModel.isWinLiveData.observe(viewLifecycleOwner) {
                if (it != null && it == true) {
                    finishLayout.visibility = View.VISIBLE
                    timerDown.cancel()
                    starsViewModel.plus(5)
                    win.visibility = View.VISIBLE
                } else if (it != null && it == false) {
                    finishLayout.visibility = View.VISIBLE
                }
            }


        }

        return binding.root
    }

    private fun restart() {
        with(binding){
            finishLayout.visibility = View.GONE
            win.visibility = View.GONE
            timerDown.start()
            bug1.visibility= View.INVISIBLE
            bug2.visibility= View.INVISIBLE
            bug3.visibility= View.INVISIBLE
            bug4.visibility= View.INVISIBLE
            bug5.visibility= View.INVISIBLE
            bug6.visibility= View.INVISIBLE
            bug7.visibility= View.INVISIBLE
            bug8.visibility= View.INVISIBLE
            bug9.visibility= View.INVISIBLE
            bugsViewModel.getBugs()

        }

    }

    private fun updateTime() {
        val timeNow = time / 1000
        timerText.text = timeNow.toString()
    }

    private fun animateFrog(imageView: ImageView , isAudioActive:Boolean) {
        val frogStartX = frogImageView.x
        val frogStartY = frogImageView.y
        val frogWidth = frogImageView.width

        val targetX = imageView.x
        val targetY = imageView.y
        val targetWidth = imageView.width.toFloat()
        val targetHeight = imageView.height.toFloat()

        val translateX = targetX - frogStartX + targetWidth / 2f - frogWidth / 2f
        val translateY = targetY - frogStartY + targetHeight

        val translateAnimationX =
            ObjectAnimator.ofFloat(frogImageView, View.TRANSLATION_X, 0f, translateX)
        val translateAnimationY =
            ObjectAnimator.ofFloat(frogImageView, View.TRANSLATION_Y, 0f, translateY)

        val scaleXAnimation = ObjectAnimator.ofFloat(frogImageView, View.SCALE_X, 1f, 1.5f)
        val scaleYAnimation = ObjectAnimator.ofFloat(frogImageView, View.SCALE_Y, 1f, 1.5f)

        val scaleXAnimationReverse = ObjectAnimator.ofFloat(frogImageView, View.SCALE_X, 1.5f, 1f)
        val scaleYAnimationReverse = ObjectAnimator.ofFloat(frogImageView, View.SCALE_Y, 1.5f, 1f)

        val animatorSet = AnimatorSet()
        animatorSet.play(translateAnimationX).with(translateAnimationY).before(scaleXAnimation)
            .before(scaleYAnimation).before(scaleXAnimationReverse).before(scaleYAnimationReverse)
        animatorSet.interpolator = AccelerateDecelerateInterpolator()
        animatorSet.duration = 100

        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                if (isAudioActive){
                    mediaPlayer.start()
                }
                super.onAnimationStart(animation)
            }
            override fun onAnimationEnd(animation: Animator) {
                imageView.visibility = View.INVISIBLE

                val returnAnimationX =
                    ObjectAnimator.ofFloat(frogImageView, View.TRANSLATION_X, translateX, 0f)
                val returnAnimationY =
                    ObjectAnimator.ofFloat(frogImageView, View.TRANSLATION_Y, translateY, 0f)

                val returnAnimatorSet = AnimatorSet()
                returnAnimatorSet.play(returnAnimationX).with(returnAnimationY)
                returnAnimatorSet.interpolator = AccelerateDecelerateInterpolator()
                returnAnimatorSet.duration = 100
                returnAnimatorSet.start()
            }
        })

        animatorSet.start()
    }


    override fun onDestroy() {
        timerDown.cancel()
        super.onDestroy()
    }

    private fun checkWin(pos: Int) {
        frogPositions.remove(pos)
        if (frogPositions.isEmpty()) {
            bugsViewModel.win()
            starsViewModel.plus(countOfStars)
        }
    }

    override fun onDestroyView() {
        mediaPlayer.stop()
        super.onDestroyView()
    }
}









