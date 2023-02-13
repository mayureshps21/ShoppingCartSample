package com.kotlin.mvvm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.mvvm.data.local.shoppingcart.ShoppingCart
import com.kotlin.mvvm.data.local.shoppingcart.ShoppingCartDao

/**
 * Created by Waheed on 04,November,2019
 */

/**
 * App Database
 * Define all entities and access doa's here/ Each entity is a table.
 */
@Database(entities = [ShoppingCart::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun shoppingCartDao(): ShoppingCartDao

}