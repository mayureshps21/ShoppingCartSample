package com.kotlin.mvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.mvvm.domain.useCase.AddItemToCartUseCase
import com.kotlin.mvvm.domain.useCase.ValidUserSessionUsecase
import com.kotlin.mvvm.ui.viewState.AddItemToCartState
import com.kotlin.mvvm.utils.ApplicationConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddToCartViewModel @Inject constructor(
    var validUserSessionUsecase: ValidUserSessionUsecase,
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
            validUserSessionUsecase.isSessionExpired()
                .collect { sessionState ->
                when (sessionState) {
                    ApplicationConstant.SESSION_EXPIRED -> {
                        validUserSessionUsecase.refreshSession().collect {
                            when (it) {
                                ApplicationConstant.SESSION_REFRESHED -> {
                                    addItemToCartUseCase.addItemToCart().collect {
                                        _addToCartSharedFlow.emit(AddItemToCartState.SUCCESS)
                                    }
                                }
                                ApplicationConstant.SESSION_REFRESHED_FAILED -> {
                                    //user must log in again
                                    _addToCartSharedFlow.emit(AddItemToCartState.FAILED)
                                }
                            }
                        }
                    }
                    ApplicationConstant.SESSION_VALID -> {
                        addItemToCartUseCase.addItemToCart()
                        _addToCartSharedFlow.emit(AddItemToCartState.SUCCESS)
                    }

                }
            }
        }


    }
}