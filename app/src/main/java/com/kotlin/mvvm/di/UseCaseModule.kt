package com.kotlin.mvvm.di

import com.kotlin.mvvm.data.useCase.AddItemToCartUsecaseImpl
import com.kotlin.mvvm.domain.repository.ValidUserSessionRepository
import com.kotlin.mvvm.domain.useCase.AddItemToCartUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
 class UseCaseModule {

    @Provides
    fun  provideUseCase(validUserSessionRepository: ValidUserSessionRepository): AddItemToCartUseCase=AddItemToCartUsecaseImpl(validUserSessionRepository)

}