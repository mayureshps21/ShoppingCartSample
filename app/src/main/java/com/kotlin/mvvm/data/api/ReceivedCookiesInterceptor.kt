package com.kotlin.mvvm.data.api

import android.content.Context
import android.content.SharedPreferences
import com.kotlin.mvvm.utils.ApplicationConstant
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class ReceivedCookiesInterceptor(val context: Context) : Interceptor {

    lateinit var sharedPreferences: SharedPreferences

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
        val originalResponse: Response = chain.proceed(chain.request())
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            val cookies: HashSet<String> = HashSet()
            for (header in originalResponse.headers("Set-Cookie")) {
                cookies.add(header)
            }
            sharedPreferences.edit()
                .putStringSet(ApplicationConstant.COOKIES, cookies)
                .apply()
        }
        return originalResponse
    }
}