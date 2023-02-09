package com.kotlin.mvvm.data.repository

import com.example.mvvm_demo.mvvm.BaseRepository
import com.kotlin.mvvm.data.api.ApiInterface
import com.kotlin.mvvm.domain.repository.AddToCartRepository
import com.kotlin.mvvm.utils.ApplicationConstant
import com.kotlin.mvvm.utils.DataProvider
import com.kotlin.mvvm.utils.DataProvider.isRefreshedlocally
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddToCartRepositoryImpl @Inject constructor(val apiInterface: ApiInterface) :
    BaseRepository(apiInterface), AddToCartRepository {
    override fun addToCart(): Flow<String> = flow {

//        apiInterface.getUser("3").also {
//            println("${it.userId},{${it.title}}")
//        }
        if (DataProvider.getSessionDetail()) {
            //refresh session
            if (DataProvider.refreshSession() && isRefreshedlocally()) {
                addToCartLocally()
                emit(ApplicationConstant.ITEM_ADDED)
            } else {
                emit(ApplicationConstant.ITEM_ADD_ERROR)
            }
        } else {
            addToCartLocally()
            emit(ApplicationConstant.ITEM_ADDED)
        }

    }

    private fun addToCartLocally() {
        //add cart locally
    }

}