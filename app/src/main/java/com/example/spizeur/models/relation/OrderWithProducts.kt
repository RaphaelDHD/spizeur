import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.spizeur.models.Order
import com.example.spizeur.models.Product
import com.example.spizeur.models.crossRef.OrderProductsCrossRef

class OrderWithProducts (
    @Embedded val order: Order,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "productId",
        associateBy = Junction(OrderProductsCrossRef::class)
    )
    val products: List<Product>
)