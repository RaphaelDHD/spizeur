package com.example.spizeur.domain.database

import com.example.spizeur.models.Order
import com.example.spizeur.models.Product
import com.example.spizeur.models.User

object DBDataSource {

    suspend fun insertUser(user: User) {
        SpizeurDataBase.getInstance().userDAO().insertUser(user)
    }

    suspend fun getUser(email: String): User {
        return SpizeurDataBase.getInstance().userDAO().getUser(email)
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

    suspend fun setUserNewUsername(username: String)
    {
        SpizeurDataBase.getInstance().userDAO().setUserNewUsername(username)
    }
    suspend fun addOrder(order: Order) {
        SpizeurDataBase.getInstance().orderDAO().insertOrder(order)
    }

    suspend fun getProductListByIds(listId: List<Int>): MutableList<Product> {
        val productList = mutableListOf<Product>()
        for (id in listId) {
            productList.add(SpizeurDataBase.getInstance().productDAO().getProductById(id))
        }
        return productList
    }

    suspend fun insertOrder(value: Order) {
        SpizeurDataBase.getInstance().orderDAO().insertOrder(value)
    }


}