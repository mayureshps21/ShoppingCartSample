package com.kotlin.mvvm.data.useCase

import com.kotlin.mvvm.domain.repository.AddToCartRepository
import com.kotlin.mvvm.domain.useCase.AddItemToCartUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddItemToCartUsecaseImpl @Inject constructor(val repository: AddToCartRepository) :
    AddItemToCartUseCase {
    override fun addItemToCart(
        id: Int,
        product: String,
        amount: String,
        address: String
    ): Flow<String> {
        return repository.addToCart(id,product,amount,address)
    }

}