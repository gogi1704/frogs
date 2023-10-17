package com.frogs.ui.viewModels

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.frogs.data.CountFrogModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ViewModelCountFrogs @Inject constructor(
    application: Application,
) : AndroidViewModel(application) {

    private var countFrogModel = CountFrogModel(-1, listOf())
        set(value) {
            field = value
            countFrogModelLiveData.value = value
        }

    val countFrogModelLiveData = MutableLiveData(countFrogModel)


    fun getFrogs() {
        val randomNum = Random.nextInt(1, 10)
        countFrogModel = CountFrogModel(randomNum, getRandomNumberList(randomNum))
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