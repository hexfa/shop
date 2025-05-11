package com.example.shop.repository

import com.example.shop.data.model.home.Slider
import com.example.shop.data.remote.BaseApiResponse
import com.example.shop.data.remote.HomeApiInterface
import com.example.shop.data.remote.NetworkResult
import javax.inject.Inject


class HomeRepository @Inject constructor(private val api:HomeApiInterface):BaseApiResponse() {



    suspend fun getSlider():NetworkResult<List<Slider>> =
        safeApiCall {
            api.getSlider()
        }
}