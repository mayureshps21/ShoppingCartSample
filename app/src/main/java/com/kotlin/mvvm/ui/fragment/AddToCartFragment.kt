package com.kotlin.mvvm.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kotlin.mvvm.R
import com.kotlin.mvvm.databinding.FragmentAddToCartBinding
import com.kotlin.mvvm.ui.activity.LoginActivity
import com.kotlin.mvvm.ui.viewState.AddItemToCartState
import com.kotlin.mvvm.ui.viewmodel.AddToCartViewModel
import com.kotlin.mvvm.utils.ToastUtil
import com.kotlin.mvvm.utils.doIfTrue
import com.kotlin.mvvm.utils.isConnectedToNetwork
import com.kotlin.mvvm.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddToCartFragment : Fragment() {
    lateinit var fragmentAddToCartBinding: FragmentAddToCartBinding
    val viewModel: AddToCartViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentAddToCartBinding.inflate(layoutInflater, container, false).also {
        fragmentAddToCartBinding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setViews()
    }

    private fun setObservers() {
        activity?.isConnectedToNetwork()?.doIfTrue {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.addToCartStateFlow.collect {
                        when (it) {
                            is AddItemToCartState.LOADING -> fragmentAddToCartBinding.progressbar.visibility =
                                View.VISIBLE
                            is AddItemToCartState.SUCCESS -> {
                                with(fragmentAddToCartBinding) {
                                    progressbar.visibility = View.GONE
                                    context?.let { it1 ->
                                        ToastUtil.showCustomToast(
                                            it1,
                                            "Item Added to Cart",
                                            Toast.LENGTH_SHORT
                                        )
                                    }
                                }
                            }
                            is AddItemToCartState.FAILED -> {
                                with(fragmentAddToCartBinding) {
                                    progressbar.visibility = View.GONE
                                    context?.let { it1 ->
                                        ToastUtil.showCustomToast(
                                            it1,
                                            "Session refresh failed!! Please log in again to add item in the cart",
                                            Toast.LENGTH_LONG
                                        )
                                    }
                                    Intent(context, LoginActivity::class.java).also {
                                        startActivity(it)
                                        requireActivity().finish()
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }?: run {
            context?.showToast(getString(R.string.no_internet))
        }
    }

    private fun setViews() {
        fragmentAddToCartBinding.addToCart.setOnClickListener {
            viewModel.addItemToCart()
        }
        fragmentAddToCartBinding.ivBack.setOnClickListener {
            requireActivity().finish()
        }

    }

}