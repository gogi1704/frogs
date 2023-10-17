package com.frogs.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.frogs.data.CountBugModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ViewModelBugs @Inject constructor(application: Application) : AndroidViewModel(application) {


    private var countBugModel = CountBugModel(-1, listOf())
        set(value) {
            field = value
            countBugModelLiveData.value = value
        }

    val countBugModelLiveData = MutableLiveData(countBugModel)

    private var isWin:Boolean? = null
        set(value) {
            field = value
            isWinLiveData.value = value
        }

    val isWinLiveData = MutableLiveData(isWin)


    fun win(){
        isWin = true
    }

    fun lose(){
        isWin = false
    }


    fun getBugs() {
        val randomNum = Random.nextInt(5, 10)
        countBugModel = CountBugModel(randomNum, getRandomNumberList(randomNum))
    }


    private fun getRandomNumberList(n: Int): List<Int> {
        val numberList = mutableListOf<Int>()

        for (i in 1..n) {
            var randomNumber: Int
            do {
                randomNumber = (1..n).random()
            } while (randomNumber in numberList)

            numberList.add(randomNumber)
        }

        return numberList
    }
}