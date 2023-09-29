package com.example.spizeur.models.services

import com.example.spizeur.models.Product
import com.example.spizeur.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("user")
    suspend fun getUsers(): Response<User>

    @GET("product/search")
    suspend fun getProductByKeyWord(@Query("q") type : String): Response<Product>

    @GET("product/{id}")
    suspend fun getProductById(@Path("id") id : Int): Response<Product>

}