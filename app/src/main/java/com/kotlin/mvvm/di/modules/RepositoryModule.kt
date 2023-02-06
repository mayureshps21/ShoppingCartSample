package com.kotlin.mvvm.di.modules

import com.kotlin.mvvm.data.repository.AddToCartRepositoryImpl
import com.kotlin.mvvm.data.repository.ValidSessionRepoImpl
import com.kotlin.mvvm.domain.repository.AddToCartRepository
import com.kotlin.mvvm.domain.repository.ValidSessionRepo
import com.kotlin.mvvm.utils.baseMVVM.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
     fun provideAddToCartRepository(apiInterface:ApiInterface): AddToCartRepository=AddToCartRepositoryImpl(apiInterface)

    @Provides
    fun provideValidSessionRepository(apiInterface: ApiInterface): ValidSessionRepo = ValidSessionRepoImpl(apiInterface)

}