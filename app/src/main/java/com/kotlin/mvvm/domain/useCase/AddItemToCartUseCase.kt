package com.kotlin.mvvm.domain.useCase

import kotlinx.coroutines.flow.Flow

interface AddItemToCartUseCase{
    fun addItemToCart( id:Int,product:String,amount:String,address:String): Flow<String>
}