package com.example.shop.data.remote

sealed class NetworkResult<T>(
    val message: String? = null,
    val data: T? = null,


    ) {
    class Success<T>(message: String?, data: T?) : NetworkResult<T>(message, data)


    class Loading<T>() : NetworkResult<T>()


    class Error<T>(message: String?, data: T? = null) : NetworkResult<T>(message, data)

}