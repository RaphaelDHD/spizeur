package com.example.spizeur.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.spizeur.R
import com.example.spizeur.databinding.FragmentHomeBinding
import com.example.spizeur.ui.productInfo.ProductInfoActivity
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val vm: HomeViewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.fetchProducts()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.productsLiveData.observe(viewLifecycleOwner, Observer {
            showByCategory()
        })
    }

    private fun showByCategory() {
        val productsByCategory = vm.sortProductsByCategory()
        val container = binding.Categories

            for (productCategory in productsByCategory) {
                val categoryTitle = TextView(this.context)
                categoryTitle.text = productCategory.key
                container.addView(categoryTitle)

                val horizontalScrollView = HorizontalScrollView(this.context)
                container.addView(horizontalScrollView)

                val linearLayout = LinearLayout(this.context)
                linearLayout.orientation = LinearLayout.HORIZONTAL
                horizontalScrollView.addView(linearLayout)

                for ((index, product) in productCategory.value.withIndex()) {
                    val fragmentView = layoutInflater.inflate(
                        R.layout.product_fragment,
                        null
                    )

                    fragmentView.setOnClickListener {
                        vm.setSelectedProduct(product)
                        startActivity(Intent(this.context, ProductInfoActivity::class.java))
                    }

                    val titleTextView = fragmentView.findViewById<TextView>(R.id.Title)
                    val priceTextView = fragmentView.findViewById<TextView>(R.id.Price)

                    titleTextView.text = product.title
                    priceTextView.text = "${product.price}€"


                    val imageUrl = product.thumbnail
                    val image = fragmentView.findViewById<ImageView>(R.id.ProductImage)
                    Picasso.get().load(imageUrl).into(image)

                    // Ajoutez une marge à droite de 16 pixels, sauf pour le dernier élément
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    if (index < productCategory.value.size - 1) {
                        layoutParams.setMargins(0, 0, 16, 0)
                    }
                    fragmentView.layoutParams = layoutParams

                    linearLayout.addView(fragmentView)
                }
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}