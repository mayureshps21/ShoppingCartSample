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


        // we'll use this function in all
        // repos to handle api errors.
        suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {

            // Returning api response
            // wrapped in Resource class
            return withContext(Dispatchers.IO) {
                try {

                    // Here we are calling api lambda
                    // function that will return response
                    // wrapped in Retrofit's Response class
                    val response: Response<T> = apiToBeCalled()

                    if (response.isSuccessful) {
                        // In case of success response we
                        // are returning Resource.Success object
                        // by passing our data in it.
                        Resource.success(data = response.body()!!)
                    } else {

                        Resource.error( response.errorBody().toString()?: "Something went wrong")
                    }

                } catch (e: HttpException) {
                    // Returning HttpException's message
                    // wrapped in Resource.Error
                    Resource.error( e.message ?: "Something went wrong")
                } catch (e: IOException) {
                    // Returning no internet message
                    // wrapped in Resource.Error
                    Resource.error("Please check your network connection")
                } catch (e: Exception) {
                    // Returning 'Something went wrong' in case
                    // of unknown error wrapped in Resource.Error
                    Resource.error( "Something went wrong")
                }
            }
        }



}