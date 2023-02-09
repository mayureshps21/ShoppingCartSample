package com.kotlin.mvvm.data.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class RetryInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var response = chain.proceed(request)
        var tryCount = 0
        while (!response.isSuccessful && tryCount < 3) {
            Log.d("intercept", "Request is not successful - " + tryCount);
            tryCount++

            // retry the request
            response.close()
            response = chain.proceed(request)
        }

        // otherwise just pass the original response on
        return response;
    }
}