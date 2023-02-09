package com.kotlin.mvvm.ui.viewmodel

//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.neverEqualPolicy
import com.kotlin.mvvm.domain.useCase.AddItemToCartUseCase
import com.kotlin.mvvm.domain.useCase.ValidUserSessionUsecase
import com.kotlin.mvvm.utils.ApplicationConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
//import kotlinx.coroutines.test.*
//import org.junit.*
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.mockito.Mockito.never
//import org.mockito.MockitoAnnotations


class AddToCartViewModelTest {

    private lateinit var viewModel: AddToCartViewModel

    /*@OptIn(ExperimentalCoroutinesApi::class)
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
        Mockito.`when`(userSessionUsecase.isSessionExpired()).thenReturn(flow<String> {
            emit(ApplicationConstant.SESSION_VALID)
        })
        viewModel.addItem.invoke()
        Mockito.verify(addItemToCartUseCase).addItemToCart()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun addItemToCartSuccessWhenSessionExpiredTest() = runTest(dispatcher) {
        Mockito.`when`(userSessionUsecase.isSessionExpired()).thenReturn(flow<String> {
            emit(ApplicationConstant.SESSION_EXPIRED)
        })
        Mockito.`when`(userSessionUsecase.refreshSession()).thenReturn(flow<String> {
            emit(ApplicationConstant.SESSION_REFRESHED)
        })
        viewModel.addItem.invoke()
        Mockito.verify(addItemToCartUseCase).addItemToCart()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun addItemToCartSuccessWhenSessionRefreshedFailedTest() = runTest(dispatcher) {
        Mockito.`when`(userSessionUsecase.isSessionExpired()).thenReturn(flow<String> {
            emit(ApplicationConstant.SESSION_EXPIRED)
        })
        Mockito.`when`(userSessionUsecase.refreshSession()).thenReturn(flow<String> {
            emit(ApplicationConstant.SESSION_REFRESHED_FAILED)
        })
        viewModel.addItem.invoke()
        Mockito.verify(addItemToCartUseCase, never()).addItemToCart()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
*/

}