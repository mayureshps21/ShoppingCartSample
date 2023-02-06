package com.kotlin.mvvm.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.mvvm.repository.db.shoppingcart.UserSessionDao
import com.kotlin.mvvm.repository.model.shoppingcart.UserSession

/**
 * Created by --
 */

/**
 * App Database
 * Define all entities and access doa's here/ Each entity is a table.
 */
@Database(entities = [UserSession::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userSessionDao(): UserSessionDao
}