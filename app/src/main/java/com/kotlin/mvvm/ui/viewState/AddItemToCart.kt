package com.kotlin.mvvm.ui.viewState

sealed class AddItemToCart{
    object LOADING:AddItemToCart()
    object SUCCESS:AddItemToCart()
    object FAILED:AddItemToCart()
}
