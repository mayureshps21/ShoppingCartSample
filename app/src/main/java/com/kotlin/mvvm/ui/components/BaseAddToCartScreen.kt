package com.kotlin.mvvm.ui.components

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.kotlin.mvvm.ui.viewState.AddItemToCartState
import com.kotlin.mvvm.ui.viewmodel.AddToCartViewModel
import com.kotlin.mvvm.utils.CartScreen
import com.kotlin.mvvm.utils.ToastUtil
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun BaseAddToCartScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddToCartViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    val showProgressbarState = remember { mutableStateOf(false) }
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(true) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                viewModel.addToCartSharedFlow.collectLatest {
                    when (it) {
                        is AddItemToCartState.LOADING -> {
                            showProgressbarState.value = true
                        }
                        is AddItemToCartState.SUCCESS -> {
                            ToastUtil.showCustomToast(
                                context,
                                "Item Added to Cart",
                                Toast.LENGTH_SHORT
                            )
                            showProgressbarState.value = false

                        }
                        is AddItemToCartState.FAILED -> {
                            ToastUtil.showCustomToast(
                                context,
                                "Session refresh failed!! Please log in again to add item in the cart",
                                Toast.LENGTH_SHORT
                            )
                            navController.navigate(CartScreen.LoginScreen.route)
                            showProgressbarState.value = false

                        }


                        else -> {
                            //
                        }
                    }
                }
            }
        }
    }
    Scaffold(modifier,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {
                                activity.finish()
                        }, modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Icon(Icons.Filled.ArrowBack, "", tint = Color.White)
                    }
                }, actions = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier.padding(horizontal = 16.dp).offset(x = (20).dp)
                    ) {
                        Icon(Icons.Filled.ShoppingCart, "", tint = Color.White)
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier.padding(horizontal = 16.dp).offset(x = (-5).dp)
                    ) {
                        Icon(Icons.Outlined.Favorite, "", tint = Color.White)
                    }
                },
                backgroundColor = Color(0xFF0F0F4A)
            )
        },
        scaffoldState = rememberScaffoldState(),
        content = { paddingValues ->
            AddToCartScreen(paddingValues = paddingValues, showProgressBar = showProgressbarState ,activity,context) {
                viewModel.addItem.invoke()
            }
        })
}



