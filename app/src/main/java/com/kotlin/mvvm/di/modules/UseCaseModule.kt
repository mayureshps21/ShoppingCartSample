package com.kotlin.mvvm.di.modules

import com.kotlin.mvvm.data.useCase.AddItemToCartUsecaseImpl
import com.kotlin.mvvm.data.useCase.ValidUserSessionUsecaseImpl
import com.kotlin.mvvm.domain.repository.AddToCartRepository
import com.kotlin.mvvm.domain.repository.ValidSessionRepo
import com.kotlin.mvvm.domain.useCase.AddItemToCartUseCase
import com.kotlin.mvvm.domain.useCase.ValidUserSessionUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
 class UseCaseModule {

    @Provides
    fun  provideAddItemToCartUseCase(validUserSessionRepository: AddToCartRepository): AddItemToCartUseCase=AddItemToCartUsecaseImpl(validUserSessionRepository)

    @Provides
    fun provideValidSessionUseCase(validUserSessionRepository: ValidSessionRepo): ValidUserSessionUsecase = ValidUserSessionUsecaseImpl(validUserSessionRepository)

}