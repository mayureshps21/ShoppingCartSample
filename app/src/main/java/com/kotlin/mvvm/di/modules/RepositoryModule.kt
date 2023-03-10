package com.kotlin.mvvm.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.kotlin.mvvm.data.repository.AddToCartRepositoryImpl
import com.kotlin.mvvm.domain.repository.AddToCartRepository
import com.kotlin.mvvm.domain.repository.ValidSessionRepo
import com.kotlin.mvvm.data.api.ApiInterface
import com.kotlin.mvvm.data.local.shoppingcart.ShoppingCartDao
import com.kotlin.mvvm.data.repository.ValidSessionRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
     fun provideAddToCartRepository(apiInterface: ApiInterface,@ApplicationContext context: Context,validSessionRepo: ValidSessionRepo,  shoppingCartDao: ShoppingCartDao): AddToCartRepository=AddToCartRepositoryImpl(apiInterface, context,validSessionRepo,shoppingCartDao)

    @Provides
     fun provideValidSessionRepository(apiInterface: ApiInterface): ValidSessionRepo=ValidSessionRepoImpl(apiInterface)


}