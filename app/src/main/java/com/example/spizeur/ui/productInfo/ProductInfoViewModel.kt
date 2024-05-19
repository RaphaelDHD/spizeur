package com.example.spizeur.ui.productInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spizeur.domain.ProductsRepository
import com.example.spizeur.domain.UserRepository
import com.example.spizeur.models.Product

class ProductInfoViewModel : ViewModel() {


    private var _isFavorite: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun getSelectedProduct(): Product? {
        return ProductsRepository.selectedProduct.value
    }

    fun setInformation(product: Product?) {
        _isFavorite.postValue(
            UserRepository.isProductFavorite(product!!.id)
        )
    }

    suspend fun onPressFavorite() {
        val product = getSelectedProduct()
        // if the product is already in the favorite list
        if (_isFavorite.value!!) {
            UserRepository.removeProductFromFavorite(product!!.id)
            _isFavorite.postValue(false)
        } else {
            UserRepository.addProductToFavorite(product!!.id)
            _isFavorite.postValue(true)
        }
    }


}