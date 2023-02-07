package com.kotlin.mvvm.utils

import java.util.*

object DataProvider {

    val random = Random()

    fun rand(from: Int, to: Int) : Int {
        return random.nextInt(to - from) + from
    }

    fun getSessionDetail():Boolean{
        return (rand(1,10)%2 == 0)
    }

    fun refreshSession():Boolean{
        return (rand(1,10)%2 == 0)
    }
}