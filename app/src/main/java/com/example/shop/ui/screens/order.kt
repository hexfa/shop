package com.example.shop.ui.screens

import com.example.shop.data.model.ResponseResult
import com.example.shop.data.model.home.Slider
import com.example.shop.data.remote.NetworkResult
import retrofit2.Response
import retrofit2.http.GET

data class Order(
    val id: String,
    val date: String,
    val total: Double,
    val status: String
)
interface HomeApiInterface {
    @GET("v1/getSlider")
    suspend fun getSlider(): Response<ResponseResult<List<Slider>>>

    @GET("v1/getOrders")
    suspend fun getOrders(): Response<ResponseResult<List<Order>>>
}


interface OrderRepository {
    suspend fun getOrders(): NetworkResult<List<Order>>
}