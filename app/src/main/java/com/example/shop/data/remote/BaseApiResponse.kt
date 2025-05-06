package com.example.shop.data.remote

import com.example.shop.data.model.ResponseResult
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall:suspend()-> Response<ResponseResult<T>>):NetworkResult<T> =
    try{

        val response=apiCall()

        if (response.isSuccessful){
            val body=response.body()
            body?.let {
                return NetworkResult.Success(body.message,body.data)
            }
        }
        return
    }catch (){

    }

    private fun <T> error(errorMessage:String):NetworkResult<T> =
        NetworkResult.Error(errorMessage)
}