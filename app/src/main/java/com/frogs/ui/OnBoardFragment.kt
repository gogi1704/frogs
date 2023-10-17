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
import com.frogs.databinding.FragmentOnBoardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(layoutInflater, container, false)
        val pref = requireActivity().getSharedPreferences(PREF_OPEN_NAME, Context.MODE_PRIVATE)


        binding.start.setOnClickListener {
            pref.edit()
                .putBoolean(PREF_OPEN_VALUE, false)
                .apply()
            findNavController().navigate(R.id.menuFragment)
        }


        val backCallBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backCallBack)


        return binding.root
    }


}