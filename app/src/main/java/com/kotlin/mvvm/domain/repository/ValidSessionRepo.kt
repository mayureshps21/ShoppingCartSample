package com.kotlin.mvvm.domain.repository

interface ValidSessionRepo {
    fun checkIfUserValid(): String
    fun refreshSession(): String
}