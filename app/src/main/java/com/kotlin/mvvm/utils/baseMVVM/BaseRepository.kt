package com.example.mvvm_demo.mvvm

import com.kotlin.mvvm.data.api.ApiInterface
import com.kotlin.mvvm.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class BaseRepository @Inject constructor(private val apiInterface : ApiInterface) {

        suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {

            return withContext(Dispatchers.IO) {
                try {
                    val response: Response<T> = apiToBeCalled()

                    if (response.isSuccessful) {
                        Resource.success(data = response.body()!!)
                    } else {

                        Resource.error( response.errorBody().toString()?: "Something went wrong")
                    }

                } catch (e: HttpException) {
                    Resource.error( e.message ?: "Something went wrong")
                } catch (e: IOException) {
                    Resource.error("Please check your network connection")
                } catch (e: Exception) {
                    Resource.error( "Something went wrong")
                }
            }
        }



}