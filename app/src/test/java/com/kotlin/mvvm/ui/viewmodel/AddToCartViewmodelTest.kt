package com.kotlin.mvvm.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.kotlin.mvvm.domain.useCase.AddItemToCartUseCase
import com.kotlin.mvvm.ui.viewState.AddItemToCartState
import com.kotlin.mvvm.utils.ApplicationConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
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
    private lateinit var addItemToCartUseCase: AddItemToCartUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        viewModel = AddToCartViewModel(addItemToCartUseCase)
        viewModel.addItemToCartUseCase = addItemToCartUseCase
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun addItemToCartSuccessTest(): Unit = runTest(dispatcher) {
        val flow=flow { emit(ApplicationConstant.ITEM_ADDED) }
        Mockito.`when`(addItemToCartUseCase.addItemToCart(id, product, amount, address))
            .thenReturn(flow)
        viewModel.addItem.invoke()
        flow.test {
            Assert.assertEquals(this.awaitItem(),ApplicationConstant.ITEM_ADDED)
            awaitComplete()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun addItemToCartFailedTest() = runTest(dispatcher) {
        val flow=flow { emit(ApplicationConstant.ITEM_ADD_ERROR) }

        Mockito.`when`(addItemToCartUseCase.addItemToCart(id, product, amount, address))
            .thenReturn(flow)
        viewModel.addItem.invoke()
        flow.test {
            Assert.assertEquals(this.awaitItem(),ApplicationConstant.ITEM_ADD_ERROR)
            awaitComplete()
        }
    }

//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test(expected =HttpException::class)
//    fun addItemToCartFailedWithExceptionTest()  = runTest {
//        Mockito.`when`(addItemToCartUseCase.addItemToCart(id, product, amount, address))
//            .thenThrow(HttpException(
//                Response.error<Any>(
//                    404,
//                    ResponseBody.create("plain/text".toMediaTypeOrNull(), "Something broke!")
//                )
//            ))
//            try{
//                viewModel.addItem.invoke()
//            }catch (e:Exception){
//                Assert.assertEquals("Something broke!", e.message)
//            }
//
//
//    }



    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


}