package com.example.spizeur.ui

import android.os.Bundle
import android.util.Log
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.spizeur.R
import com.example.spizeur.databinding.ActivityMainBinding
import com.example.spizeur.models.Product
import com.example.spizeur.models.services.ApiClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigationContainer

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home_fragment, R.id.nav_profile_fragment
            )
        )

        executeCall()

        findViewById<Toolbar>(R.id.toolbar)
            .setupWithNavController(navController, appBarConfiguration)

//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun sortProductsByCategory(products: List<Product>): MutableMap<String, MutableList<Product>> {
        val productsByCategory = mutableMapOf<String, MutableList<Product>>()

        for (product in products) {
            if (!productsByCategory.containsKey(product.category)) {
                productsByCategory[product.category] = mutableListOf()
            }
            productsByCategory[product.category]?.add(product)
        }
        return productsByCategory
    }

    private fun showByCategory(productsByCategory: MutableMap<String, MutableList<Product>>) {
        val container = findViewById<LinearLayout>(R.id.Categories) // Référence à votre LinearLayout dans le XML

        runOnUiThread(Runnable {
            for (productCategory in productsByCategory) {
                val categoryTitle = TextView(this@MainActivity)
                categoryTitle.text = productCategory.key
                container.addView(categoryTitle)

                val horizontalScrollView = HorizontalScrollView(this@MainActivity)
                container.addView(horizontalScrollView)

                val linearLayout = LinearLayout(this@MainActivity)
                linearLayout.orientation = LinearLayout.HORIZONTAL
                horizontalScrollView.addView(linearLayout)

                for ((index, product) in productCategory.value.withIndex()) {
                    val fragmentView = layoutInflater.inflate(R.layout.product_fragment, null) // Remplacez "your_fragment_layout" par le nom de votre fragment XML
                    val titleTextView = fragmentView.findViewById<TextView>(R.id.Title)
                    val priceTextView = fragmentView.findViewById<TextView>(R.id.Price)

                    titleTextView.text = product.title
                    priceTextView.text = product.price.toString() + "€"

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
        })
    }


    @OptIn(DelicateCoroutinesApi::class)
    private fun executeCall() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.apiService.getAllProduct()
                if (response.isSuccessful && response.body() != null) {
                    val content = response.body()
                    val products = content?.products
                    val productsByCategory = sortProductsByCategory(products!!)

                    showByCategory(productsByCategory)
                    Log.d("API_TEST", "Reponse correcte")
                } else {
                    Log.d("API_TEST", "Reponse pas correcte")
                }

            } catch (e: Exception) {
                Log.e("API_TEST", "${e.message}")
            }
        }
    }

    private fun Int.dpToPx(): Int {
        val density = resources.displayMetrics.density
        return (this * density).toInt()
    }

}