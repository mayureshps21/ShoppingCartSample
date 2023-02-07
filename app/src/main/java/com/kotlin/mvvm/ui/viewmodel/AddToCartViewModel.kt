package com.kotlin.mvvm.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.mvvm.domain.useCase.AddItemToCartUseCase
import com.kotlin.mvvm.domain.useCase.ValidUserSessionUsecase
import com.kotlin.mvvm.ui.viewState.AddItemToCartState
import com.kotlin.mvvm.utils.ApplicationConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddToCartViewModel @Inject constructor(
    var validUserSessionUsecase: ValidUserSessionUsecase,
    var addItemToCartUseCase: AddItemToCartUseCase,
) : ViewModel() {
    private var _addToCartStateFlow = MutableStateFlow<AddItemToCartState>(AddItemToCartState.IDLE)
    val addToCartStateFlow: StateFlow<AddItemToCartState>
        get() = _addToCartStateFlow.asStateFlow()

    fun addItemToCart() {
        _addToCartStateFlow.value = AddItemToCartState.LOADING
        viewModelScope.launch {
            validUserSessionUsecase.isSessionExpired().collect { sessionState ->
                when (sessionState) {
                    ApplicationConstant.SESSION_EXPIRED -> {
                        validUserSessionUsecase.refreshSession().collect{
                            when(it) {
                                ApplicationConstant.SESSION_REFRESHED -> {
                                    addItemToCartUseCase.addItemToCart().collect{
                                        _addToCartStateFlow.value = AddItemToCartState.SUCCESS
                                    }
                                }
                                ApplicationConstant.SESSION_REFRESHED_FAILED -> {
                                    //user must log in again
                                    _addToCartStateFlow.value = AddItemToCartState.FAILED
                                }
                            }
                        }
                    }

                }
            }
        }


    }
}