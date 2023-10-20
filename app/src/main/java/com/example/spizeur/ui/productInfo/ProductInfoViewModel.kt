package com.example.spizeur.ui.productInfo

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.spizeur.domain.ProductsRepository
import com.example.spizeur.models.Product

class ProductInfoViewModel: ViewModel() {


    fun getSelectedProduct(): Product? {
        return ProductsRepository.selectedProduct.value
    }
}