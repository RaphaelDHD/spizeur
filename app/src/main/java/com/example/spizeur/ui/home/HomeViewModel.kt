package com.example.spizeur.ui.home

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

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    //en privée le le livedata est mutable
    private var _productsLiveData : MutableLiveData<Response<Products>?> = MutableLiveData<Response<Products>?>()
    //on expose le liveData en non mutable
    val productsLiveData : LiveData<Response<Products>?> = _productsLiveData

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

    fun sortProductsByCategory(): MutableMap<String, MutableList<Product>> {
        val productsByCategory = mutableMapOf<String, MutableList<Product>>()

        for (product in _productsLiveData.value?.body()?.productList!!) {
            if (!productsByCategory.containsKey(product.category)) {
                productsByCategory[product.category] = mutableListOf()
            }
            productsByCategory[product.category]?.add(product)
        }
        return productsByCategory
    }




}