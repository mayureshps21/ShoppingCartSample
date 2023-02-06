package com.kotlin.mvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kotlin.mvvm.databinding.FragmentAddToCartBinding
import com.kotlin.mvvm.ui.viewmodel.AddToCartViewModel
import dagger.hilt.android.AndroidEntryPoint

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
        setViews()
    }

    private fun setViews() {
        fragmentAddToCartBinding.addToCart.setOnClickListener {
            viewModel.addItemToCart()
        }

        fragmentAddToCartBinding.ivCart.setOnClickListener {
            //
        }
    }

}