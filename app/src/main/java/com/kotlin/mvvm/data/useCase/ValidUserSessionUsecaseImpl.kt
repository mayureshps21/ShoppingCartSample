package com.kotlin.mvvm.data.useCase

import com.kotlin.mvvm.domain.repository.ValidSessionRepo
import com.kotlin.mvvm.domain.useCase.ValidUserSessionUsecase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidUserSessionUsecaseImpl @Inject constructor(val repo: ValidSessionRepo) :
    ValidUserSessionUsecase {
    override fun isSessionExpired(): Flow<String> = repo.checkIfUserValid()
    override fun refreshSession():Flow<String> = repo.refreshSession()
}