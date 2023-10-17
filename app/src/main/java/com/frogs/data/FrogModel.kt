package com.frogs.data

import com.frogs.R

data class FrogModel(
    val id: Int,
    val frogType: FrogType,
    val imageId: Int,
    val isOpen:Boolean = false,
    val imageDefaultId: Int = R.drawable.frog_new

)


sealed class FrogType

data object GreenFrog : FrogType()
data object RedFrog : FrogType()
data object YellowFrog : FrogType()

