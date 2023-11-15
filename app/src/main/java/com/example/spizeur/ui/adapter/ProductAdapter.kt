package com.example.spizeur.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spizeur.R
import com.example.spizeur.models.Product

class ProductAdapter (private var productData: List<Product>):
    RecyclerView.Adapter<ProductAdapter.DataViewHolder>()
{
    private var productsList: List<Product> = ArrayList()

    init {
        this.productsList = productData
    }

    var onItemClick: ((String) -> Unit)? = null

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(productsList[adapterPosition].title)
            }
        }

        fun bind(result : Product) {
            itemView.findViewById<TextView>(R.id.name).text = result.title

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_product, parent, false
        )
    )
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(productsList[position])
    }

    override fun getItemCount(): Int = productsList.size


}