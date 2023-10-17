package com.frogs.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.frogs.R
import com.frogs.databinding.FragmentGameCountFrogsBinding
import com.frogs.ui.viewModels.StarsViewModel
import com.frogs.ui.viewModels.ViewModelCountFrogs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameCountFrogsFragment : Fragment() {
    private lateinit var binding: FragmentGameCountFrogsBinding
    private val viewModelCountFrogs: ViewModelCountFrogs by viewModels()
    private val viewModelStars: StarsViewModel by viewModels()
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameCountFrogsBinding.inflate(layoutInflater, container, false)

        viewModelCountFrogs.getFrogs()

        with(binding) {
            text1.setOnClickListener {
                if (count == 1) {
                    viewModelStars.plus()
                } else {
                    Toast.makeText(requireContext() , "Error" , Toast.LENGTH_SHORT).show()
                }
                restart()
            }
            text2.setOnClickListener {
                if (count == 2) {
                    viewModelStars.plus()
                } else {
                    Toast.makeText(requireContext() , "Error" , Toast.LENGTH_SHORT).show()
                }
                restart()
            }
            text3.setOnClickListener {
                if (count == 3) {
                    viewModelStars.plus()
                } else {
                    Toast.makeText(requireContext() , "Error" , Toast.LENGTH_SHORT).show()
                }
                restart()
            }
            text4.setOnClickListener {
                if (count == 4) {
                    viewModelStars.plus()
                } else {
                    Toast.makeText(requireContext() , "Error" , Toast.LENGTH_SHORT).show()
                }
                restart()
            }
            text5.setOnClickListener {
                if (count == 5) {
                    viewModelStars.plus()
                } else {
                    Toast.makeText(requireContext() , "Error" , Toast.LENGTH_SHORT).show()
                }
                restart()
            }

            text6.setOnClickListener {
                if (count == 6) {
                    viewModelStars.plus()
                } else {
                    Toast.makeText(requireContext() , "Error" , Toast.LENGTH_SHORT).show()
                }
                restart()
            }
            text7.setOnClickListener {
                if (count == 7) {
                    viewModelStars.plus()
                } else {
                    Toast.makeText(requireContext() , "Error" , Toast.LENGTH_SHORT).show()
                }
                restart()
            }
            text8.setOnClickListener {
                if (count == 8) {
                    viewModelStars.plus()
                } else {
                    Toast.makeText(requireContext() , "Error" , Toast.LENGTH_SHORT).show()
                }
                restart()
            }
            text9.setOnClickListener {
                if (count == 9) {
                    viewModelStars.plus()
                } else {
                    Toast.makeText(requireContext() , "Error" , Toast.LENGTH_SHORT).show()
                }
                restart()
            }
            text10.setOnClickListener {
                if (count == 10) {
                    viewModelStars.plus()
                } else {
                    Toast.makeText(requireContext() , "Error" , Toast.LENGTH_SHORT).show()
                }
                restart()
            }

            buttonBack.setOnClickListener {
                findNavController().navigateUp()
            }


            viewModelCountFrogs.countFrogModelLiveData.observe(viewLifecycleOwner) {
                if (it.count != -1) {
                    count = it.count
                    for (i in it.frogPositions) {
                        when (i) {
                            1 -> {
                                frog1.visibility = View.VISIBLE
                            }

                            2 -> {
                                frog2.visibility = View.VISIBLE

                            }

                            3 -> {
                                frog3.visibility = View.VISIBLE

                            }

                            4 -> {
                                frog4.visibility = View.VISIBLE

                            }

                            5 -> {
                                frog5.visibility = View.VISIBLE

                            }

                            6 -> {
                                frog6.visibility = View.VISIBLE

                            }

                            7 -> {
                                frog7.visibility = View.VISIBLE

                            }

                            8 -> {
                                frog8.visibility = View.VISIBLE

                            }

                            9 -> {
                                frog9.visibility = View.VISIBLE

                            }
                        }
                    }
                }
            }

            viewModelStars.countLiveData.observe(viewLifecycleOwner){
                starsNum.text = it.toString()
            }
        }

        return binding.root
    }

    fun restart(){
        with(binding){
            frog1.visibility = View.GONE
            frog2.visibility = View.GONE
            frog3.visibility = View.GONE
            frog4.visibility = View.GONE
            frog5.visibility = View.GONE
            frog6.visibility = View.GONE
            frog7.visibility = View.GONE
            frog8.visibility = View.GONE
            frog9.visibility = View.GONE
            viewModelCountFrogs.getFrogs()

        }
    }


}