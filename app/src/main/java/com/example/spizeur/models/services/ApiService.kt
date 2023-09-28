package com.example.spizeur.models.services

import com.example.spizeur.models.Product
import com.example.spizeur.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("user")
    suspend fun getUsers(): Response<MutableList<User>>

    @GET("product/search?q={type}")
    suspend fun getProductByKeyWord(@Path("type") type : String): Response<MutableList<Product>>

    @GET("product/{id}")
    suspend fun getProductById(@Path("id") id : Int): Response<Product>

}