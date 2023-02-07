package com.kotlin.mvvm.data.repository

import com.example.mvvm_demo.mvvm.BaseRepository
import com.kotlin.mvvm.data.api.ApiInterface
import com.kotlin.mvvm.domain.repository.AddToCartRepository
import com.kotlin.mvvm.utils.ApplicationConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddToCartRepositoryImpl @Inject constructor(val apiInterface: ApiInterface) :
    BaseRepository(apiInterface), AddToCartRepository {
    override fun addToCart(): Flow<String> = flow {
        //add to cart
//        apiInterface.addToCart()
        addToCartLocally()
        emit(ApplicationConstant.ITEM_ADDED)
    }

    private fun addToCartLocally() {
        TODO("Not yet implemented")
    }
}