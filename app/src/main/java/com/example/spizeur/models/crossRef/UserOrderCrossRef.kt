package com.example.spizeur.models.crossRef

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["userId", "orderId"])
data class UserOrderCrossRef(
    val userId: Int,
    val orderId: Int
)
