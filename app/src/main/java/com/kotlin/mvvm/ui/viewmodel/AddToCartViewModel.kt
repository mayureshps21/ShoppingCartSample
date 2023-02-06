package com.kotlin.mvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.mvvm.ui.viewState.AddItemToCart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddToCartViewModel @Inject constructor(
    var getAddItemToCartUseCase: GetAddItemToCartUseCase
) : ViewModel() {
 private var _addToCartStateFlow= MutableStateFlow(AddItemToCart.LOADING)
    val addToCartStateFlow:StateFlow<AddItemToCart>
    get=_addToCartStateFlow.asStateFlow()

    fun addItemToCart() {
        viewModelScope.launch {
            getAddToCartUseCase.addItemToCart()
        }
    }
}