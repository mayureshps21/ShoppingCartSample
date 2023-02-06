package com.kotlin.mvvm.data.repository

import com.kotlin.mvvm.domain.repository.ValidSessionRepo
import com.kotlin.mvvm.utils.ApplicationConstant
import com.kotlin.mvvm.utils.baseMVVM.ApiInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidSessionRepoImpl @Inject constructor(val apiInterface: ApiInterface) : ValidSessionRepo {
    override fun checkIfUserValid(): Flow<String> = flow {
        if (apiInterface.isSessionExpired()) {
            //refresh session
            emit(ApplicationConstant.SESSION_EXPIRED)
        } else if (apiInterface.isSessionRefreshedSuccesfully())
            emit(ApplicationConstant.SESSION_REFRESHED)
        else emit(ApplicationConstant.SESSION_REFRESHED_FAILED)
    }

    override fun refreshSession() {
        apiInterface.refreshSession()
    }
}


