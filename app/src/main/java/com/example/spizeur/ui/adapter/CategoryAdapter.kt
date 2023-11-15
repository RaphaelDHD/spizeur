package com.example.spizeur.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spizeur.R
import com.example.spizeur.models.Products


open class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.DataViewHolder>()
{
    var categoriesList: List<Products> = ArrayList()

    var onItemClick: ((Products) -> Unit)? = null

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(categoriesList[adapterPosition])
            }
        }

        fun bind(result: Products) {
            itemView.findViewById<TextView>(R.id.content_title).text = result.productList[0].category
            val childMembersAdapter = ProductAdapter(result.productList)
            val childRecyclerView = itemView.findViewById<RecyclerView>(R.id.child_recycler_view)
            childRecyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL,false)
            childRecyclerView.adapter = childMembersAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_category, parent, false
        )
    )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(categoriesList[position])
    }

    override fun getItemCount(): Int = categoriesList.size

    fun addData(list: List<Products>) {
        categoriesList = list
        notifyDataSetChanged()
    }


}