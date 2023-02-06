package com.kotlin.mvvm.utils.baseMVVM

import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("")
    fun isSessionExpired():Boolean
    @GET("")
    fun refreshSession():Boolean
    @POST
    fun addToCart()
}