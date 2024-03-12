import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spizeur.R
import com.example.spizeur.domain.ProductsRepository
import com.example.spizeur.domain.UserRepository
import com.example.spizeur.models.Product
import com.example.spizeur.ui.productInfo.ProductInfoActivity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class CartAdapter(private val productList: List<Product>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_cart, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product = productList[position]

        Picasso.get().load(product.thumbnail).into(holder.image)
        holder.title.text = product.title
        holder.quantity.text = "1"
        holder.price.text = product.price.toString()
        holder.seeDetails.setOnClickListener {
            ProductsRepository.setSelectedProduct(product)
            val intent = Intent(holder.itemView.context, ProductInfoActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
        holder.delete.setOnClickListener {
            GlobalScope.launch{
                UserRepository.removeFromCart(position)
            }
            notifyItemRemoved(position)
        }


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return productList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val image = ItemView.findViewById<ImageView>(R.id.productImageCart)
        val title = ItemView.findViewById<TextView>(R.id.productTitleCart)
        val quantity = ItemView.findViewById<TextView>(R.id.productQuantityCart)
        val price = ItemView.findViewById<TextView>(R.id.productPriceCart)
        val seeDetails = ItemView.findViewById<Button>(R.id.seeDetailsButtonCart)
        val delete = ItemView.findViewById<ImageButton>(R.id.deleteFromCartButton)
    }

}