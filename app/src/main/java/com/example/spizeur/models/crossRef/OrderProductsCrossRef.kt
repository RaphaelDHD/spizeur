package com.example.spizeur.models.crossRef

import androidx.room.Entity

@Entity(primaryKeys = ["orderId", "productId"])
data class OrderProductsCrossRef(
    val userId: Int,
    val orderId: Int
)
