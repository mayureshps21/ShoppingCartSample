package com.kotlin.mvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.mvvm.domain.useCase.AddItemToCartUseCase
import com.kotlin.mvvm.ui.viewState.AddItemToCartState
import com.kotlin.mvvm.utils.ApplicationConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddToCartViewModel @Inject constructor(
    var addItemToCartUseCase: AddItemToCartUseCase,
) : ViewModel() {
    private var _addToCartSharedFlow = MutableSharedFlow<AddItemToCartState>()
    val addToCartSharedFlow: SharedFlow<AddItemToCartState>
        get() = _addToCartSharedFlow.asSharedFlow()
    val addItem: () -> Unit = {
        viewModelScope.launch { addItemToCart() }

    }

    private suspend fun addItemToCart() {
        _addToCartSharedFlow.emit(AddItemToCartState.LOADING)
        viewModelScope.launch {
            addItemToCartUseCase.addItemToCart(1,"wallet","$75","Washington D.C")
                .catch { throwable ->
                    _addToCartSharedFlow.emit(AddItemToCartState.ERROR(throwable.message.toString()))
                }
                .collect {
                    when (it) {
                        ApplicationConstant.ITEM_ADDED -> {
                            _addToCartSharedFlow.emit(AddItemToCartState.SUCCESS)
                        }
                        else -> {
                            _addToCartSharedFlow.emit(AddItemToCartState.FAILED)
                        }
                    }
                }
        }


    }
}