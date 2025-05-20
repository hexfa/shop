package com.example.shop.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.shop.data.model.ResponseResult
import com.example.shop.data.model.home.Slider
import com.example.shop.data.remote.BaseApiResponse
import com.example.shop.data.remote.NetworkResult
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
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

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Basket : Screen("basket")
    object Category : Screen("category")
    object Profile : Screen("profile")
    object OrderHistory : Screen("order_history")
}


@Composable
fun OrderHistoryScreen(
    navController: NavController,
    viewModel: OrderHistoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val isRefreshing = uiState is UiState.Loading

    LaunchedEffect(Unit) { viewModel.fetchOrders() }

    SwipeRefresh(
        state = SwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.fetchOrders() }
    ) {
      /*  when (uiState) {
            is UiState.Loading -> LoadingIndicator()
            is UiState.Success -> OrderList((uiState as UiState.Success<List<Order>>).data)
            is UiState.Error -> ErrorView((uiState as UiState.Error).message) { viewModel.fetchOrders() }
        }*/
    }

    @Composable
    fun LoadingIndicator() = Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }


    @Composable
    fun ErrorView(message: String, onRetry: () -> Unit) = Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(text = message, color = MaterialTheme.colors.error)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry) { Text("Retry") }
    }

    @Composable
    fun OrderList(orders: List<Order>) = LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(orders) { order ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                elevation = 2.dp
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Order #${order.id}")
                        Text(text = order.date)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(text = "\$${order.total}")
                        Text(text = order.status)
                    }
                }
            }
        }
    }
}


