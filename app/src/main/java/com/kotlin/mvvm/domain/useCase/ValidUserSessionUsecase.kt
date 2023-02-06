package com.kotlin.mvvm.domain.useCase

import kotlinx.coroutines.flow.Flow

interface ValidUserSessionUsecase {
    fun isSessionExpired(): Flow<String>
    fun refreshSession(): Flow<String>
}