package com.kotlin.mvvm.repository.model.shoppingcart

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "session_table")
data class UserSession(@PrimaryKey(autoGenerate = true) var id: Int = 0,
                       @SerializedName("session") var session: String? = null,
                       @SerializedName("token") var token: String? = null
)