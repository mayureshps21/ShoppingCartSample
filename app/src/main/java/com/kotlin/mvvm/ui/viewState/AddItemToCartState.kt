package com.kotlin.mvvm.ui.viewState

sealed class AddItemToCartState{
    object IDLE:AddItemToCartState()
    object LOADING:AddItemToCartState()
    object SUCCESS:AddItemToCartState()
    object FAILED:AddItemToCartState()
    class ERROR(val error: String) : AddItemToCartState()
}
