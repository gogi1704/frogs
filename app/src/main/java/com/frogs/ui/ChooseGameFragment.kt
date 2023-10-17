package com.frogs.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.frogs.R
import com.frogs.databinding.FragmentChooseGameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseGameFragment : Fragment(){
    private lateinit var binding: FragmentChooseGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseGameBinding.inflate(layoutInflater, container, false)

        with(binding){
            gameBugs.setOnClickListener {
                findNavController().navigate(R.id.action_chooseGameFragment_to_gameBugsFragment)
            }

            gameFrogs.setOnClickListener {
                findNavController().navigate(R.id.action_chooseGameFragment_to_gameFrogsFragment)

            }

            gameCountFrogs.setOnClickListener {
                findNavController().navigate(R.id.action_chooseGameFragment_to_gameCountFrogsFragment)

            }
        }

        return binding.root
    }


}