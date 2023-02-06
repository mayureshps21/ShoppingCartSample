package com.kotlin.mvvm.domain.repository

import kotlinx.coroutines.flow.Flow

interface AddToCartRepository {
    fun addToCart(): Flow<String>
}