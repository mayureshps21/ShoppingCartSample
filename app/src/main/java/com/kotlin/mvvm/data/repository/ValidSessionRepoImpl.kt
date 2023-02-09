package com.kotlin.mvvm.data.repository

import com.kotlin.mvvm.data.api.ApiInterface
import com.kotlin.mvvm.domain.repository.ValidSessionRepo
import com.kotlin.mvvm.utils.ApplicationConstant
import com.kotlin.mvvm.utils.DataProvider
import javax.inject.Inject

class ValidSessionRepoImpl @Inject constructor(val apiInterface: ApiInterface) : ValidSessionRepo {
    override fun checkIfUserValid(): String {
        if (DataProvider.getSessionDetail()) {
            return ApplicationConstant.SESSION_EXPIRED
        } else {
            return (ApplicationConstant.SESSION_VALID)
        }

    }

    override fun refreshSession(): String {
        if (DataProvider.refreshSession() && isRefreshedlocally()) {
            return ApplicationConstant.SESSION_REFRESHED
        } else {
            return (ApplicationConstant.SESSION_REFRESHED_FAILED)
        }
    }

    private fun isRefreshedlocally(): Boolean {
        // local refreshing of session
        return true
    }
}


