package com.kotlin.mvvm.repository.db.shoppingcart

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kotlin.mvvm.repository.model.news.News
import com.kotlin.mvvm.repository.model.shoppingcart.UserSession

@Dao
interface UserSessionDao {

    /**
     * Insert user session information into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserSessionInformation(session: UserSession): Long

    /**
     * Get session information from database
     */
    @Query("SELECT * FROM session_table")
    fun getUserSession(): LiveData<List<UserSession>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSessionInformation(session: UserSession): Long

}