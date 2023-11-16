package com.example.spizeur.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spizeur.domain.ProductsRepository
import com.example.spizeur.models.Product
import com.example.spizeur.models.Products
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber


class HomeViewModel: ViewModel() {

    //en privée le livedata est mutable
    private var _productsLiveData : MutableLiveData<Response<Products>?> = MutableLiveData<Response<Products>?>()
    //on expose le liveData en non mutable
    val productsLiveData : LiveData<Response<Products>?> = _productsLiveData

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        //lance une coroutine pour effectuer un traitement
        viewModelScope.launch {
            // appel de l'api
            ProductsRepository.getProducts()
                //si erreur
                .catch {
                    Timber.e(it)
                }
                .collect {
                    //on post le resultat aux observer
                    it.isSuccessful
                    it.body()?.productList
                    _productsLiveData.postValue(it)
                }
        }
    }

    fun sortProductsByCategory(): MutableList<Products> {
        val productsByCategory = mutableListOf<Products>()
        _productsLiveData.value?.body()?.productList?.let { productList ->
            val categories = mutableSetOf<String>()

            for (product in productList) {
                if (!categories.contains(product.category)) {
                    categories.add(product.category)
                    productsByCategory.add(Products(mutableListOf(product)))
                } else {
                    productsByCategory[categories.indexOf(product.category)].productList.add(product)
                }
            }
        }
        return productsByCategory
    }

    fun setSelectedProduct(product: Product) {
        ProductsRepository.setSelectedProduct(product)
    }


}