package com.example.shop.ui.screens

import com.example.shop.data.model.ResponseResult
import com.example.shop.data.model.home.Slider
import com.example.shop.data.remote.BaseApiResponse
import com.example.shop.data.remote.NetworkResult
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

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

class OrderRepositoryImpl @Inject constructor(
    private val api: HomeApiInterface
) : OrderRepository, BaseApiResponse() {
    override suspend fun getOrders(): NetworkResult<List<Order>> =
        safeApiCall { api.getOrders() }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindOrderRepository(
        impl: OrderRepositoryImpl
    ): OrderRepository
}

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}