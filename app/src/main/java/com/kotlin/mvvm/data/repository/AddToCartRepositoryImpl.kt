package com.kotlin.mvvm.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.mvvm_demo.mvvm.BaseRepository
import com.kotlin.mvvm.data.api.ApiInterface
import com.kotlin.mvvm.data.local.shoppingcart.ShoppingCart
import com.kotlin.mvvm.data.local.shoppingcart.ShoppingCartDao
import com.kotlin.mvvm.domain.repository.AddToCartRepository
import com.kotlin.mvvm.domain.repository.ValidSessionRepo
import com.kotlin.mvvm.utils.AppExecutors
import com.kotlin.mvvm.utils.ApplicationConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddToCartRepositoryImpl @Inject constructor(val apiInterface: ApiInterface, val context: Context, val validSessionRepo: ValidSessionRepo, var sharedPreferences: SharedPreferences, val shoppingCartDao: ShoppingCartDao, private val appExecutors: AppExecutors = AppExecutors()) :
    BaseRepository(apiInterface), AddToCartRepository ,ValidSessionRepo{

    /**
     * Shopping Cart Item updated in database
     */
    private fun addToCartLocally(id:Int,product:String,amount:String,address:String) {
        appExecutors.diskIO().execute {
            shoppingCartDao.insertItems(ShoppingCart(item_id = id, product = product, amount = amount, address = address))
        }
    }

    /**
     * Remove items form shopping cart
     */
    private fun removeAllCartItems(){
        appExecutors.diskIO().execute {
            shoppingCartDao.deleteAllItems()
        }
    }

    override fun addToCart(
        id: Int,
        product: String,
        amount: String,
        address: String
    ): Flow<String> = flow{
            //check session is valid or not
            if(checkIfUserValid().equals(ApplicationConstant.SESSION_VALID) || refreshSession().equals(ApplicationConstant.SESSION_REFRESHED)){
                apiInterface.getUser("3").also {
                    println(it.userId)
                }
                addToCartLocally(id,product,amount,address)
                emit(ApplicationConstant.ITEM_ADDED)
            }else {
                removeAllCartItems()
                emit(ApplicationConstant.SESSION_INVALID)
            }
    }


    override fun checkIfUserValid(): String {
        return validSessionRepo.checkIfUserValid()
    }

    override fun refreshSession(): String = validSessionRepo.refreshSession()
}