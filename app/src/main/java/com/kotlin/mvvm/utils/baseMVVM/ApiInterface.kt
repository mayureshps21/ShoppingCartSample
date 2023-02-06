package com.kotlin.mvvm.utils.baseMVVM

interface ApiInterface {
    fun isSessionExpired():Boolean
    fun isSessionRefreshedSuccesfully():Boolean
    fun refreshSession()
    fun addToCart()
}