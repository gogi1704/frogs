package com.frogs.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StarsViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private var count = 0
        set(value) {
            field = value
            countLiveData.value = value
        }

    val countLiveData = MutableLiveData(count)

    fun plus() {
        count++
    }
    fun plus(num:Int){
        count -= num
    }

    fun minus() {
        count--
    }

}