package com.frogs.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.frogs.R
import com.frogs.databinding.FragmentMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        val pref = requireActivity().getSharedPreferences(PREF_OPEN_NAME , Context.MODE_PRIVATE)

        if (pref.getBoolean(PREF_OPEN_VALUE , true)){
            findNavController().navigate(R.id.onBoardFragment)
        }

        with(binding) {
            btPlay.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_chooseGameFragment)
            }
            btSettings.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_settingsFragment)
            }
        }

        val backCallBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backCallBack)




        return binding.root
    }

}

const val PREF_OPEN_NAME = "PREF_OPEN_NAME"
const val PREF_OPEN_VALUE = "PREF_OPEN_VALUE"