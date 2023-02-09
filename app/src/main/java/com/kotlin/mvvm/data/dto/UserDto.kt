package com.kotlin.mvvm.data.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id")
    val id:String,
    @SerializedName("userId")
    val userId:String,
    @SerializedName("title")
    val title:String)