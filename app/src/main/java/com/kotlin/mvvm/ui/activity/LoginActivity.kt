package com.kotlin.mvvm.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.mvvm.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}