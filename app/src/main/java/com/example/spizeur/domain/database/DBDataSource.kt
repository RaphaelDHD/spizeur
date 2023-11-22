package com.example.spizeur.domain.database

import com.example.spizeur.models.Product
import com.example.spizeur.models.User

object DBDataSource {

    suspend fun insertUser(user: User) {
        SpizeurDataBase.getInstance().userDAO().insertUser(user)
    }

    suspend fun addProducts(products: List<Product>) {
        SpizeurDataBase.getInstance().productDAO().insertProducts(products)
    }

    suspend fun getAllProducts(): List<Product> {
        return SpizeurDataBase.getInstance().productDAO().getAllProduct()
    }

    suspend fun deleteAllProduct() {
        SpizeurDataBase.getInstance().productDAO().deleteAllProducts()
    }



}