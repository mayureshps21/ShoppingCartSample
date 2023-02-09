package com.kotlin.mvvm.domain.useCase

import kotlinx.coroutines.flow.Flow

interface ValidUserSessionUsecase {
    fun isSessionExpired(): String
    fun refreshSession(): String
}