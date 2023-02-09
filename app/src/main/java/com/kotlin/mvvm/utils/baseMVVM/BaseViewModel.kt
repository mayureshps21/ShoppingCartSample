package com.example.mvvm_demo.mvvm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.mvvm.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(private val baseRepository : BaseRepository):ViewModel() {

    // FOR ERROR HANDLER
    protected val _snackbarError = MutableLiveData<Event<Int>>()
    val snackBarError: LiveData<Event<Int>> get() = _snackbarError

    override fun onCleared() {
        super.onCleared()
    }
}