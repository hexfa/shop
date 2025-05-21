import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

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




