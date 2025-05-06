package com.example.shop.repository

import com.example.shop.data.remote.HomeApiInterface
import javax.inject.Inject


class HomeRepository @Inject constructor(private val api:HomeApiInterface) {
}