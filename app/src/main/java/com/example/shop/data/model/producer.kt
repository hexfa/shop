import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
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

