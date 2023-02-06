package com.kotlin.mvvm.data.useCase

import com.kotlin.mvvm.domain.repository.ValidUserSessionRepository
import com.kotlin.mvvm.domain.useCase.AddItemToCartUseCase
import javax.inject.Inject

class AddItemToCartUsecaseImpl@Inject constructor(val repository: ValidUserSessionRepository) : AddItemToCartUseCase {

    override fun addItemToCart() {
        repository.checkIfUserValid()
    }
}