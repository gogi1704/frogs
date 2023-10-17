package com.frogs.ui

import android.app.Application
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridLayout
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.frogs.R
import com.frogs.data.FrogModel
import com.frogs.databinding.FragmentGameFrogsBinding
import com.frogs.ui.adapters.ClickListener
import com.frogs.ui.adapters.FrogAdapter
import com.frogs.ui.viewModels.FrogsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFrogsFragment : Fragment() {
    private lateinit var binding: FragmentGameFrogsBinding
    private val viewModel: FrogsViewModel by viewModels()
    private lateinit var adapter: FrogAdapter
    private lateinit var gridFrogs: GridView
    private var time = 0L
    private lateinit var timerText: TextView
    private lateinit var timerDown: CountDownTimer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameFrogsBinding.inflate(layoutInflater, container, false)
        viewModel.getShuffledFrogs()
        timerText = binding.timer


        timerDown = object : CountDownTimer(21 * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                time = millisUntilFinished
                updateTime()
            }

            override fun onFinish() {
                viewModel.lose()
            }
        }
        timerDown.start()


        with(binding) {
            gridFrogs = grid

            viewModel.frogsLiveData.observe(viewLifecycleOwner) {
                adapter = FrogAdapter(requireContext(),  it , object :ClickListener{
                    override fun click(id: Int) {
                        viewModel.openFrog(id)
                        adapter.notifyDataSetChanged()
                    }
                })
                gridFrogs.adapter = adapter
            }

            viewModel.loseLiveData.observe(viewLifecycleOwner){
                if (it == null){
                    finishLayout.visibility = View.GONE
                }
                if (it == true){
                    finishLayout.visibility = View.VISIBLE
                    win.text = requireContext().getString(R.string.win)
                    timerDown.cancel()
                }

                if (it==false){
                    finishLayout.visibility = View.VISIBLE
                    win.text = requireContext().getString(R.string.lose)
                    timerDown.cancel()
                }

            }

            buttonReply.setOnClickListener {
                finishLayout.visibility = View.GONE
                viewModel.getShuffledFrogs()
                timerDown.start()

            }

            buttonHome.setOnClickListener {
                findNavController().navigateUp()
            }
        }








        return binding.root
    }
    private fun updateTime() {
        val timeNow = time / 1000
        timerText.text = timeNow.toString()
    }

}