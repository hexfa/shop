package com.example.shop.data.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Singleton

data class CartItem(
    val id: String,
    val name: String,
    val price: Double,
    val quantity: Int
)

interface CartRepository {
    fun getCartItems(): Flow<List<CartItem>>
    suspend fun removeItem(itemId: String)
    suspend fun clearCart()
}


data class CartUiState(
    val items: List<CartItem> = emptyList(),
    val totalPrice: Double = 0.0
)

class CartViewModel(
    private val repository: CartRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    init {
        repository.getCartItems()
            .onEach { items ->
                val total = items.sumOf { it.price * it.quantity }
                _uiState.value = CartUiState(items, total)
            }
            .launchIn(viewModelScope)
    }

    fun removeItem(itemId: String) {
        viewModelScope.launch {
            repository.removeItem(itemId)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
        }
    }

}

@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel(),
    onCheckout: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Your Cart") })
        },
        content = { padding ->
            Column(modifier = Modifier
                .padding(padding)
                .fillMaxSize()) {
                if (uiState.items.isEmpty()) {
                    Text("Your cart is empty", modifier = Modifier.padding(16.dp))
                } else {
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(uiState.items) { item ->
                            CartItemRow(item = item, onRemove = { viewModel.removeItem(it) })
                        }
                    }
                    Divider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Total:")
                        Text("$${"%.2f".format(uiState.totalPrice)}")
                    }
                    Button(
                        onClick = onCheckout,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Checkout")
                    }
                }
            }
        }
    )
}

@Composable
private fun CartItemRow(item: CartItem, onRemove: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(item.name, style = MaterialTheme.typography.h1)
            Text("Qty: ${item.quantity}", style = MaterialTheme.typography.h2)
        }
        Row {
            Text(
                "$${"%.2f".format(item.price * item.quantity)}",
                modifier = Modifier.alignByBaseline()
            )
            IconButton(onClick = { onRemove(item.id) }) {
                Icon(Icons.Default.Delete, contentDescription = "Remove")
            }
        }
    }


}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { /* ... */ }
        composable("cart") {
        }
        // other routes...
    }
}

class CartViewModelFactory(
    private val repository: CartRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class InMemoryCartRepository : CartRepository {

    private val _items = MutableStateFlow<List<CartItem>>(emptyList())
    override fun getCartItems(): Flow<List<CartItem>> = _items.asStateFlow()

    init {
        _items.value = listOf(
            CartItem(id = "1", name = "Sample Product", price = 19.99, quantity = 2)
        )
    }

    override suspend fun removeItem(itemId: String) {
        _items.value = _items.value.filterNot { it.id == itemId }
    }

    override suspend fun clearCart() {
        _items.value = emptyList()
    }

    @Composable
    fun CartItemRow(
        item: CartItem,
        onRemove: (String) -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(item.name, style = MaterialTheme.typography.h2)
                Text("Qty: ${item.quantity}", style = MaterialTheme.typography.h2)
            }
            Row {
                Text(
                    "$${"%.2f".format(item.price * item.quantity)}",
                    modifier = Modifier.alignByBaseline()
                )
                IconButton(onClick = { onRemove(item.id) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Remove")
                }
            }
        }
    }

    @Composable
    fun CartScreen(
        onCheckout: () -> Unit,
        viewModel: CartViewModel = hiltViewModel()
    ) {
        val uiState = viewModel.uiState.collectAsState().value

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Your Cart") },
                    actions = {
                        IconButton(onClick = { viewModel.clearCart() }) {
                            Icon(Icons.Default.Delete, contentDescription = "Clear Cart")
                        }
                    }
                )
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    if (uiState.items.isEmpty()) {
                        Text(
                            "Your cart is empty",
                            modifier = Modifier.padding(16.dp)
                        )
                    } else {
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(uiState.items) { item ->
                                CartItemRow(item = item, onRemove = { viewModel.removeItem(it) })
                            }
                        }
                        Divider()
                        Row(

                            modifier = Modifier
                                .fillMaxWidth()

                                .padding(16.dp),

                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Total:")
                            Text("$${"%.2f".format(uiState.totalPrice)}")
                        }

                        Button(
                            onClick = onCheckout,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text("Checkout")
                        }
                    }
                }
            }
        )
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class CartModule {

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        impl: InMemoryCartRepository
    ): CartRepository


}


