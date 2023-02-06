package com.kotlin.mvvm.domain.useCase

import kotlinx.coroutines.flow.Flow

interface ValidUserSessionUsecase {
    fun isSessionExpired(): Flow<String>
    fun isSessionRefreshedSuccesfully(): Flow<String>
    fun refreshSession()
}