package com.example.spizeur.ui.productInfo

import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.spizeur.R
import com.example.spizeur.databinding.ActivityProductInfoBinding
import com.example.spizeur.domain.UserRepository
import com.example.spizeur.models.Product
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class ProductInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductInfoBinding

    private val vm: ProductInfoViewModel = ProductInfoViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = vm.getSelectedProduct()
        vm.setInformation(product)
        editFavorite(vm.isFavorite.value!!)
        setInformation(product)

        val button = findViewById<Button>(R.id.AddToCart)

        button.setOnClickListener {
            lifecycleScope.launch {
                UserRepository.addToCart(product!!)
            }
            button.setText("Added !")
            button.setBackgroundColor(resources.getColor(R.color.md_theme_light_success))
        }

        findViewById<ImageButton>(R.id.command_info_back).setOnClickListener {
            finish()
        }

        findViewById<ImageButton>(R.id.command_info_share).setOnClickListener {
            // Generate the QR code here
            val qrCodeContent = product?.title + '_' + product?.id
            val qrCodeBitmap = generateQRCode(qrCodeContent)

            // Create a dialog
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.qr_code_dialog)

            // Set the QR code bitmap to the ImageView in the dialog layout
            val qrCodeImageView = dialog.findViewById<ImageView>(R.id.qrCodeImageView)
            qrCodeImageView.setImageBitmap(qrCodeBitmap)

            // Show the dialog
            dialog.show()
        }

        findViewById<FloatingActionButton>(R.id.AddToFavorite).setOnClickListener() {
            vm.onPressFavorite()
        }

        vm.isFavorite.observe(this) {
            editFavorite(it)
        }

    }

    fun setInformation(product: Product?) {
        val title = binding.Title
        val price = binding.Price
        val brand = binding.Brand
        val description = binding.Description

        title.text = product?.title
        price.text = product?.price.toString() + " €"
        brand.text = product?.brand
        description.text = product?.description

        val linearLayout = binding.ImageLinearLayout

        linearLayout.weightSum = product?.images!!.size.toFloat()

        for (imageUrl in product?.images!!) {
            var image = ImageView(this)
            Picasso.get().load(imageUrl).into(image)
            image.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
            )
            linearLayout.addView(image)
        }

    }

    private fun generateQRCode(qrCodeContent: String): Bitmap? {
        val multiFormatWriter = MultiFormatWriter()
        try {
            val bitMatrix = multiFormatWriter.encode(qrCodeContent, BarcodeFormat.QR_CODE, 400, 400)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val pixels = IntArray(width * height)
            for (y in 0 until height) {
                val offset = y * width
                for (x in 0 until width) {
                    pixels[offset + x] = if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
                }
            }
            return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
                setPixels(pixels, 0, width, 0, 0, width, height)
            }
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        return null
    }

    private fun editFavorite(isFavorite: Boolean) {
        findViewById<FloatingActionButton>(R.id.AddToFavorite).setImageResource(
            if (isFavorite) {
                R.drawable.bookmark_fill
            } else {
                R.drawable.baseline_bookmark_border_24
            }
        )
    }

}