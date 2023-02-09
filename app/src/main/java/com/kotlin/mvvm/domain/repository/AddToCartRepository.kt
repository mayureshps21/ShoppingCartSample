package com.kotlin.mvvm.domain.repository

import kotlinx.coroutines.flow.Flow

interface AddToCartRepository {
    fun addToCart(id:Int,product:String,amount:String,address:String): Flow<String>
}