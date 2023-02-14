package com.kotlin.mvvm.data.local.shoppingcart

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "shopping_cart_table")
data class ShoppingCart(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("item_id") var item_id: Int = 0,
    @SerializedName("product") var product: String? = null,
    @SerializedName("amount") var amount: String? = null,
    @SerializedName("address") var address:String = ""
)
