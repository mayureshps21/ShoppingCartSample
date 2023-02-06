package com.kotlin.mvvm.di

import com.kotlin.mvvm.data.repository.ValidUserSessionRepositoryImpl
import com.kotlin.mvvm.domain.repository.ValidUserSessionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(repository:ValidUserSessionRepositoryImpl): ValidUserSessionRepository

}