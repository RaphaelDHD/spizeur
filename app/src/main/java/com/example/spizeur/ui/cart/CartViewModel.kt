package com.example.spizeur.ui.cart

import androidx.lifecycle.ViewModel
import com.example.spizeur.domain.UserRepository
import com.example.spizeur.models.Product

class CartViewModel : ViewModel()   {
    fun getCartProducts() : List<Product> {
        return UserRepository.currentUserOrder.value?.productList?.toList() ?: emptyList()
    }
}