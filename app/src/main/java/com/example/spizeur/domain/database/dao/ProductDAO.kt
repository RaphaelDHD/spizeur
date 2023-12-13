package com.example.spizeur.domain.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.spizeur.models.Product
import com.example.spizeur.models.User

@Dao
interface ProductDAO {

    @Insert
    suspend fun insertProduct(product: Product)
    @Insert
    suspend fun insertProducts(products: List<Product>)
    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("DELETE FROM Product")
    suspend fun deleteAllProducts()

    @Query ("Select * from Product")
    suspend fun getAllProduct(): List<Product>

    @Query("SELECT * FROM Product WHERE title LIKE '%' || :searchQuery || '%'")
    suspend fun searchProduct(searchQuery: String): List<Product>

    @Query("Select * from Product where productId =:id limit 1")
    suspend fun getProductById(id: Int): Product


}