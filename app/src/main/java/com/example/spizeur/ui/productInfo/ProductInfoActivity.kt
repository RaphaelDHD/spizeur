package com.example.spizeur.ui.productInfo

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout

import androidx.appcompat.app.AppCompatActivity
import com.example.spizeur.R
import com.example.spizeur.databinding.ActivityProductInfoBinding
import com.example.spizeur.models.Product
import com.squareup.picasso.Picasso

class ProductInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductInfoBinding

    private val vm: ProductInfoViewModel = ProductInfoViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        val product = vm.getSelectedProduct()
        setInformation(product)
    }


    fun setInformation(product : Product?) {
        val title = binding.Title
        val price = binding.Price
        val brand = binding.Brand
        val description = binding.Description

        title.text = product?.title
        price.text = product?.price.toString() + " €"
        brand.text = product?.brand
        description.text = product?.description

        val linearLayout = binding.ImageLinearLayout
        for (imageUrl in product?.images!!) {
            var image = ImageView(this)
            Picasso.get().load(imageUrl).into(image)
            image.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            linearLayout.addView(image)
        }

    }


}