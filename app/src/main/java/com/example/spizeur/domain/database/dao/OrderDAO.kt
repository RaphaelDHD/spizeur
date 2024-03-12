package com.example.spizeur.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.spizeur.models.Order

@Dao
interface OrderDAO {

    @Insert
    suspend fun insertOrder(Order: Order)

    @Update
    suspend fun updateOrder(Order: Order)

    @Query("SELECT * FROM `Order` WHERE userCommandId = :userId AND commandDate is NULL LIMIT 1")
    suspend fun getOrderByUserId(userId: Int): Order

}