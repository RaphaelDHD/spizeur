package com.example.spizeur.domain

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.spizeur.domain.api.ApiClient
import com.example.spizeur.domain.database.DBDataSource
import com.example.spizeur.models.Product
import com.example.spizeur.models.Products
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object ProductsRepository {

    private var _selectedProduct : MutableLiveData<Product> = MutableLiveData<Product>()
    val selectedProduct = _selectedProduct
    suspend fun getProducts() : Flow<Response<Products>> = flow {
        //emet la valeur
        emit(ApiClient.apiService.getAllProduct())
    }

    fun setSelectedProduct(product: Product) {
        _selectedProduct.postValue(product)
    }

    suspend fun addMultipleProductToDatabase(products: List<Product>) {
        DBDataSource.addProducts(products)
    }

    suspend fun getAllProductsFromDatabase(): List<Product> {
        return DBDataSource.getAllProducts()
    }

    suspend fun deleteAllProductsFromDatabase() {
        DBDataSource.deleteAllProduct()
    }


}