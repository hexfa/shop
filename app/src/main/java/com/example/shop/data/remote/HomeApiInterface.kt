package com.example.shop.data.remote

import com.example.shop.data.model.ResponseResult
import com.example.shop.data.model.home.Slider
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiInterface {
    @GET("v1/getSlider")
    suspend fun getSlider(): Response<ResponseResult<List<Slider>>>
}