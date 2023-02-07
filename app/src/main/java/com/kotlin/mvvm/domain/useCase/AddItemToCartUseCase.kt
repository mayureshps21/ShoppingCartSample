package com.kotlin.mvvm.domain.useCase

import kotlinx.coroutines.flow.Flow

interface AddItemToCartUseCase {
    fun addItemToCart(): Flow<String>
}