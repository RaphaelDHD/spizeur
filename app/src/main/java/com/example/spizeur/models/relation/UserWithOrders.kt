package com.example.spizeur.models.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.spizeur.models.Order
import com.example.spizeur.models.User

class UserWithOrders (
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "orderId",
        associateBy = Junction(UserWithOrders::class)
    )
    val orders: List<Order>
)