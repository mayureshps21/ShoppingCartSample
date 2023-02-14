package com.kotlin.mvvm.di.modules


import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.kotlin.mvvm.data.api.AddCookiesInterceptor
import com.kotlin.mvvm.utils.ApplicationConstant
import com.kotlin.mvvm.data.api.ApiInterface
import com.kotlin.mvvm.data.api.ReceivedCookiesInterceptor
import com.kotlin.mvvm.data.api.RetryInterceptor
import com.kotlin.mvvm.data.local.AppDatabase
import com.kotlin.mvvm.data.local.shoppingcart.ShoppingCartDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * App Module dependencies
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor,sharedPreferences: SharedPreferences): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(RetryInterceptor())
            .addInterceptor(ReceivedCookiesInterceptor(sharedPreferences))
            .addInterceptor(AddCookiesInterceptor(sharedPreferences))
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Provides ApiServices client for Retrofit
     */
    @Singleton
    @Provides
    fun provideService(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApplicationConstant.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provide network API call service
     */
    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)

    /**
     * Provides app AppDatabase
     */
    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "costco-db")
            .fallbackToDestructiveMigration().build()

    /**
     * Provides ShoppingCartDao an object to access Shopping Cart table from Database
     */
    @Singleton
    @Provides
    fun provideShoppingCartDao(db: AppDatabase): ShoppingCartDao = db.shoppingCartDao()
}
