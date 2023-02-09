package com.kotlin.mvvm.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kotlin.mvvm.domain.useCase.AddItemToCartUseCase
import com.kotlin.mvvm.domain.useCase.ValidUserSessionUsecase
import com.kotlin.mvvm.utils.ApplicationConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class AddToCartViewModelTest {

    private lateinit var viewModel: AddToCartViewModel
    val id = 1
    val product = "wallet"
    val amount = "$75"
    val address = "Washington D.C"
    @OptIn(ExperimentalCoroutinesApi::class)
    var dispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userSessionUsecase: ValidUserSessionUsecase

    @Mock
    private lateinit var addItemToCartUseCase: AddItemToCartUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        viewModel = AddToCartViewModel(userSessionUsecase, addItemToCartUseCase)
        viewModel.validUserSessionUsecase = userSessionUsecase
        viewModel.addItemToCartUseCase = addItemToCartUseCase
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun addItemToCartSuccessWhenSessionisValidTest() = runTest(dispatcher) {

        Mockito.`when`(addItemToCartUseCase.addItemToCart(id, product, amount, address))
            .thenReturn(flow { emit(ApplicationConstant.ITEM_ADDED) })
        viewModel.addItem.invoke()
        Mockito.verify(addItemToCartUseCase).addItemToCart(id, product, amount, address)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun addItemToCartSuccessWhenSessionExpiredTest() = runTest(dispatcher) {
        Mockito.`when`(addItemToCartUseCase.addItemToCart(id,product,amount,address))
            .thenReturn(flow { emit(ApplicationConstant.SESSION_EXPIRED) })
        viewModel.addItem.invoke()
        Mockito.verify(addItemToCartUseCase).addItemToCart(id,product,amount,address)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun addItemToCartSuccessWhenSessionRefreshedFailedTest() = runTest(dispatcher) {
        Mockito.`when`(addItemToCartUseCase.addItemToCart(id,product,amount,address))
            .thenReturn(flow { emit(ApplicationConstant.SESSION_REFRESHED_FAILED) })
        viewModel.addItem.invoke()
        Mockito.verify(addItemToCartUseCase).addItemToCart(id,product,amount,address)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


}