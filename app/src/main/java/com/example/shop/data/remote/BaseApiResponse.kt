package com.example.shop.data.remote

import com.example.shop.data.model.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall:suspend()-> Response<ResponseResult<T>>):NetworkResult<T> =

        withContext(Dispatchers.IO){
            try{

                val response=apiCall()

                if (response.isSuccessful){
                    val body=response.body()
                    body?.let {
                        return NetworkResult.Success(body.message,body.data)
                    }
                }
                return error("code :${response.code()}  message:${response.message()}")
            }catch (e: Exception){
                return error(e.message?:e.toString())

            }
        }


    private fun <T> error(errorMessage:String):NetworkResult<T> =
        NetworkResult.Error("Api call failed: $errorMessage")
}