package com.kotlin.mvvm.di.modules

import com.kotlin.mvvm.data.useCase.AddItemToCartUsecaseImpl
import com.kotlin.mvvm.domain.repository.AddToCartRepository
import com.kotlin.mvvm.domain.useCase.AddItemToCartUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
 class UseCaseModule {
    @Provides
    fun  provideAddItemToCartUseCase(validUserSessionRepository: AddToCartRepository): AddItemToCartUseCase=AddItemToCartUsecaseImpl(validUserSessionRepository)

}