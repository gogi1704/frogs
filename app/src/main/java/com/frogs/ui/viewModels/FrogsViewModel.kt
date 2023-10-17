package com.frogs.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frogs.R
import com.frogs.data.FrogModel
import com.frogs.data.GreenFrog
import com.frogs.data.RedFrog
import com.frogs.data.YellowFrog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FrogsViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {


    private var frogs = emptyList<FrogModel>()
        set(value) {
            field = value
            frogsLiveData.value = value
        }
    val frogsLiveData = MutableLiveData(frogs)

    private var isWin: Boolean? = null
        set(value) {
            field = value
            loseLiveData.value = value
        }
    val loseLiveData = MutableLiveData(isWin)


    fun openFrog(id: Int) {
        if (frogs.filter { it.id == id }[0].frogType == GreenFrog) {
            frogs = frogs.map { if (it.id == id) it.copy(isOpen = true) else it }

            if (frogs.filter { it.isOpen }.size == 5){
                isWin = true
            }
        } else {
            viewModelScope.launch {
                frogs = frogs.map { it.copy(isOpen = true) }
                delay(1500)
                frogs = frogs.map { it.copy(isOpen = false) }
            }

        }



    }

    fun lose(){
        isWin = false
    }

    fun getShuffledFrogs() {
        frogs = frogsList.shuffled()
    }

    private val frogsList = listOf(
        FrogModel(1, YellowFrog, R.drawable.frog_yellow),
        FrogModel(2, YellowFrog, R.drawable.frog_yellow),
        FrogModel(3, YellowFrog, R.drawable.frog_yellow),
        FrogModel(4, YellowFrog, R.drawable.frog_yellow),
        FrogModel(5, YellowFrog, R.drawable.frog_yellow),
        FrogModel(6, GreenFrog, R.drawable.frog),
        FrogModel(7, GreenFrog, R.drawable.frog),
        FrogModel(8, GreenFrog, R.drawable.frog),
        FrogModel(9, GreenFrog, R.drawable.frog),
        FrogModel(10, GreenFrog, R.drawable.frog),
        FrogModel(11, RedFrog, R.drawable.frog_red),
        FrogModel(12, RedFrog, R.drawable.frog_red),
        FrogModel(13, RedFrog, R.drawable.frog_red),
        FrogModel(14, RedFrog, R.drawable.frog_red),
        FrogModel(15, RedFrog, R.drawable.frog_red),
        FrogModel(16, RedFrog, R.drawable.frog_red),
    )
}