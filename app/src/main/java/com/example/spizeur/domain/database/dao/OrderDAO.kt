package com.example.spizeur.domain.database.dao

import OrderWithProducts
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.spizeur.models.Order
import com.example.spizeur.models.Product

@Dao
interface OrderDAO {

    @Insert
    suspend fun insertOrder(Order: Order)

}