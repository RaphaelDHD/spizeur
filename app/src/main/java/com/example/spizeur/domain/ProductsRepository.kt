package com.example.spizeur.domain

import com.example.spizeur.domain.api.ApiClient
import com.example.spizeur.models.Products
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object ProductsRepository {
    suspend fun getProducts() : Flow<Response<Products>> = flow {
        //emet la valeur
        emit(ApiClient.apiService.getAllProduct())
    }
}