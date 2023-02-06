package com.kotlin.mvvm.data.useCase

import com.kotlin.mvvm.domain.repository.ValidSessionRepo
import com.kotlin.mvvm.domain.useCase.ValidUserSessionUsecase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidUserSessionUsecaseImpl @Inject constructor(val repo: ValidSessionRepo) :
    ValidUserSessionUsecase {
    override fun isSessionExpired(): Flow<String> = flow { repo.checkIfUserValid() }

    override fun isSessionRefreshedSuccesfully(): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun refreshSession() =repo.refreshSession()
}