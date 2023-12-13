package com.example.spizeur.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.spizeur.models.Order

@Dao
interface OrderDAO {

    @Insert
    suspend fun insertOrder(Order: Order)

}