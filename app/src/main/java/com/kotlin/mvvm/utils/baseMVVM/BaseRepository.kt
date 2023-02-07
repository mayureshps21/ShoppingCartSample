package com.example.mvvm_demo.mvvm

import com.kotlin.mvvm.data.api.ApiInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class BaseRepository @Inject constructor(private val apiInterface : ApiInterface) {
}