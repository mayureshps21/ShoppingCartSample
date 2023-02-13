package com.kotlin.mvvm.data.local.shoppingcart

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShoppingCartDao {
    /**
     * Insert item into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(cartItem: ShoppingCart): Long

    /**
     * Get all the cart item from database
     */
    @Query("SELECT * FROM shopping_cart_table")
    fun getShoppingCartItems(): LiveData<List<ShoppingCart>>

    /**
     * Delete all items from cart
     */
    @Query("DELETE FROM shopping_cart_table")
    abstract fun deleteAllItems()
}