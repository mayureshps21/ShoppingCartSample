package com.kotlin.mvvm.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.mvvm.databinding.ActivityShoppingCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingCartActivity : AppCompatActivity() {
    lateinit var activityShoppingCartBinding: ActivityShoppingCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityShoppingCartBinding = ActivityShoppingCartBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
    }
}