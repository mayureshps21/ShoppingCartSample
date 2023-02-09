package com.kotlin.mvvm.data.repository

import android.content.Context
import com.example.mvvm_demo.mvvm.BaseRepository
import com.kotlin.mvvm.data.api.ApiInterface
import com.kotlin.mvvm.domain.repository.AddToCartRepository
import com.kotlin.mvvm.domain.repository.ValidSessionRepo
import com.kotlin.mvvm.utils.ApplicationConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddToCartRepositoryImpl @Inject constructor(val apiInterface: ApiInterface, val context: Context,val validSessionRepo: ValidSessionRepo) :
    BaseRepository(apiInterface), AddToCartRepository ,ValidSessionRepo{
    val sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
    val editor= sharedPreferences.edit()

    private fun addToCartLocally(id:Int,product:String,amount:String,address:String) {
        editor.apply{
            putInt("item_id",id)
            putString("product",product)
            putString("amount",amount)
            putString("address",address)
        }.apply()
    }

    override fun addToCart(
        id: Int,
        product: String,
        amount: String,
        address: String
    ): Flow<String> = flow {
        //check session is valid or not
        if(checkIfUserValid().equals(ApplicationConstant.SESSION_VALID) || refreshSession().equals(ApplicationConstant.SESSION_REFRESHED)){
            apiInterface.getUser("3").also {
                println(it.userId)
            }
            addToCartLocally(id,product,amount,address)
            emit(ApplicationConstant.ITEM_ADDED)
        }else{
            emit(ApplicationConstant.SESSION_INVALID)

        }

    }

    override fun checkIfUserValid():String{
        return validSessionRepo.checkIfUserValid()
    }

    override fun refreshSession():String =validSessionRepo.refreshSession()
}