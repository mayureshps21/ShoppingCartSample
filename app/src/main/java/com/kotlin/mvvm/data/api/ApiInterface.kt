package com.kotlin.mvvm.data.api

import com.kotlin.mvvm.data.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiInterface {
    @GET("todos/{id}")
    suspend fun getUser(@Path("id") id: String):UserDto
}