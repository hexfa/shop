package com.example.shop.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.shop.data.model.ResponseResult
import com.example.shop.data.model.home.Slider
import com.example.shop.data.remote.BaseApiResponse
import com.example.shop.data.remote.NetworkResult
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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


@HiltViewModel
class OrderHistoryViewModel @Inject constructor(
    private val repository: OrderRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Order>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Order>>> = _uiState

    fun fetchOrders() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            when (val result = repository.getOrders()) {
                is NetworkResult.Success -> _uiState.value = UiState.Success(result.data ?: emptyList())
                is NetworkResult.Error -> _uiState.value = UiState.Error(result.message ?: "Unknown error")
                else -> _uiState.value = UiState.Error("Unexpected state")
            }
        }
    }
}


fun NavGraphBuilder.orderHistoryGraph(navController: NavHostController) {
    /*composable(Screen.OrderHistory.route) {
        OrderHistoryScreen(navController)
    }*/
}