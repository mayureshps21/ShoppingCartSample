package com.example.mvvm_demo.mvvm

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(private val baseRepository : BaseRepository, @ApplicationContext private val context : Context):ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }
}