import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spizeur.R
import com.example.spizeur.models.Product
import com.squareup.picasso.Picasso

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

        Picasso.get().load(product.thumbnail).into(holder.imageView)
        holder.textView.text = product.title

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return productList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.cartImageView)
        val textView: TextView = itemView.findViewById(R.id.cartTextView)
    }

}