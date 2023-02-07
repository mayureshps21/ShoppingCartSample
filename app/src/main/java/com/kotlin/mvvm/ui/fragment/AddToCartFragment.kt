package com.kotlin.mvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kotlin.mvvm.databinding.FragmentAddToCartBinding
import com.kotlin.mvvm.ui.viewState.AddItemToCartState
import com.kotlin.mvvm.ui.viewmodel.AddToCartViewModel
import com.kotlin.mvvm.utils.ToastUtil
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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addToCartStateFlow.collect {
                    when (it) {
                        is AddItemToCartState.LOADING -> fragmentAddToCartBinding.progressbar.visibility =
                            View.VISIBLE
                        is AddItemToCartState.SUCCESS -> {
                            with(fragmentAddToCartBinding) {
                                context?.let { it1 -> ToastUtil.showCustomToast(it1,"Item Added to Cart") }
                                progressbar.visibility = View.GONE
                            }
                        }  is AddItemToCartState.FAILED -> {
                            with(fragmentAddToCartBinding) {
                                progressbar.visibility = View.VISIBLE
                                context?.let { it1 -> ToastUtil.showCustomToast(it1,"Session refresh failed!! Please log in again to add item in the cart") }
                            }
                        }

                    }
                }
            }
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