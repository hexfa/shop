
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Double
)

@Dao
interface ProductDao {
    @Insert
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<Product>>
}

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}

interface ProductRepository {
    suspend fun addProduct(product: Product)
    fun getProducts(): Flow<List<Product>>
}

class ProductRepositoryImpl(private val dao: ProductDao) : ProductRepository {
    override suspend fun addProduct(product: Product) = dao.insertProduct(product)
    override fun getProducts() = dao.getAllProducts()
}

class AddProductUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(product: Product) = repository.addProduct(product)
}

class ProductViewModel(private val addProductUseCase: AddProductUseCase) : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    fun addProduct(product: Product) {
        viewModelScope.launch {
            addProductUseCase(product)
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideProductDao(db: AppDatabase): ProductDao = db.productDao()

    @Provides
    fun provideProductRepository(dao: ProductDao): ProductRepository = ProductRepositoryImpl(dao)

    @Provides
    fun provideAddProductUseCase(repo: ProductRepository): AddProductUseCase = AddProductUseCase(repo)
}


@Composable
fun ProductListScreen(viewModel: ProductViewModel, onAddClick: () -> Unit) {
    val products by viewModel.products.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Add Product")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(products) { product ->
                Text("${"product"} - $${String.format("%.2f", "product.price")}",
                    modifier = Modifier.padding(16.dp))
                Divider()
            }
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddProductScreen(
    viewModel: ProductViewModel,
    onProductAdded: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(scaffoldState = scaffoldState) {
        Column(modifier = Modifier.padding(16.dp)) {
            // ... TextFields

            Button(onClick = {
                val price = "priceText.toDoubleOrNull()"
                /*if (name.isBlank() || price == null || price <= 0.0) {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Please enter valid name and price")
                    }
                } else {
                    isLoading = true
                    viewModel.addProduct(Product(name = name, price = price))
                    isLoading = false
                    onProductAdded()
                }*/
            }, modifier = Modifier.fillMaxWidth()) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                } else {
                    Text("Add Product")
                }
            }
        }
    }
}



@Composable
fun AppNavHost(viewModel: ProductViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "productList") {
        composable("productList") {
            ProductListScreen(viewModel) {
                navController.navigate("addProduct")
            }
        }
        composable("addProduct") {
            AddProductScreen(viewModel) {
                navController.popBackStack()
            }
        }
    }


}
@Composable
fun ConfirmDeleteDialog(
    product: Product,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Product") },
        text = { Text("Are you sure you want to delete ${product.name}?") },
        confirmButton = {
            TextButton(onClick = onConfirm) { Text("Delete") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}













