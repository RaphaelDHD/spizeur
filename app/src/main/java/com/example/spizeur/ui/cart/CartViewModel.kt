package com.example.spizeur.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spizeur.domain.UserRepository
import com.example.spizeur.models.Product
import kotlinx.coroutines.launch

class CartViewModel : ViewModel()   {
    fun getCartProducts() : List<Product> {
        return UserRepository.currentUserOrder.value?.productList?.toList() ?: emptyList()
    }

    fun command() {
        viewModelScope.launch {
            UserRepository.command()
        }
    }
}