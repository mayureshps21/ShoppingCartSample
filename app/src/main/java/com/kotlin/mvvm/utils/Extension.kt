package com.kotlin.mvvm.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

/*check the wifi state*/
fun Context.isConnectedToNetwork(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
}

inline fun Boolean.doIfTrue(first: () -> Unit): Boolean? {
    return if (this) {
        first()
        true
    } else
        null
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
