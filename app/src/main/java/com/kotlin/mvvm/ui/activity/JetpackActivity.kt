package com.kotlin.mvvm.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kotlin.mvvm.ui.components.AddToCartScreen
import com.kotlin.mvvm.utils.CartScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JetpackActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = CartScreen.MainCartScreen.route
                    ) {
                        composable(route = CartScreen.MainCartScreen.route) {
                                AddToCartScreen(navController = navController)
                        }
                        composable(
                            route = CartScreen.LoginScreen.route,
                        ) {
                            //
                        }
                    }
                }
            }
        }


    }
}




