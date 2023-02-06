package com.kotlin.mvvm.domain.repository

import kotlinx.coroutines.flow.Flow

interface ValidSessionRepo {
    fun checkIfUserValid():Flow<String>
    fun refreshSession():Flow<String>
}