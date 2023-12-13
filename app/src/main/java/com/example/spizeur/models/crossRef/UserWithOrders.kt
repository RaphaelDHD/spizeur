package com.example.spizeur.models.crossRef

import androidx.room.Embedded
import androidx.room.Relation
import com.example.spizeur.models.Order
import com.example.spizeur.models.User

data class UserWithOrders(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userCommandId"
    )
    val orders: List<Order>
)