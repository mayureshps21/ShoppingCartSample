package com.kotlin.mvvm.utils

sealed class CartScreen(val route: String) {
    object MainCartScreen : CartScreen("add_to_cart_screen")
    object LoginScreen : CartScreen("login_screen")


}