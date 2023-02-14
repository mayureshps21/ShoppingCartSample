package com.kotlin.mvvm.di.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferenceModule {

    /**
     * Provides Preferences object with MODE_PRIVATE
     */
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context):SharedPreferences=
        context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
}