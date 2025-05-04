package com.example.shop.data.remote

sealed class NetworkResult<T>(
    val data:T?=null,
    val message:String?=null

)