package com.kotlin.mvvm.utils.baseMVVM

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mvvm_demo.mvvm.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import com.kotlin.mvvm.utils.setupSnackbar

abstract class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSnackbar(this, getViewModel().snackBarError, Snackbar.LENGTH_LONG)
    }

    abstract fun getViewModel(): BaseViewModel

}