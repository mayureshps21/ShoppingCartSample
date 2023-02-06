package com.kotlin.mvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.mvvm.domain.useCase.AddItemToCartUseCase
import com.kotlin.mvvm.ui.viewState.AddItemToCartState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddToCartViewModel @Inject constructor(
    var getAddItemToCartUseCase: AddItemToCartUseCase
) : ViewModel() {
    private var _addToCartStateFlow = MutableStateFlow<AddItemToCartState>(AddItemToCartState.IDLE)
    val addToCartStateFlow: StateFlow<AddItemToCartState>
        get() = _addToCartStateFlow.asStateFlow()

    fun addItemToCart() {
        _addToCartStateFlow.value=AddItemToCartState.LOADING
        viewModelScope.launch {
            getAddItemToCartUseCase.addItemToCart()
        }
    }
}