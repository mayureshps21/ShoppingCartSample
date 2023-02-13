package com.kotlin.mvvm.data.api

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.kotlin.mvvm.utils.ApplicationConstant
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class AddCookiesInterceptor @Inject constructor(val sharedPreferences: SharedPreferences) : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        builder.addHeader("Accept","application/json")
//        sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
        sharedPreferences.getStringSet(ApplicationConstant.COOKIES,HashSet())?.let {
            for (cookie in sharedPreferences.getStringSet(ApplicationConstant.COOKIES,HashSet())!!) {

                if(cookie.contains("sid")) {
                    builder.addHeader("Cookie", cookie)
                    Log.v(
                        "OkHttp",
                        "Adding Header: $cookie"
                    )
                }
            }
        }

        return chain.proceed(builder.build())
    }
}