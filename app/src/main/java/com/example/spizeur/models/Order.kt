package com.example.spizeur.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
class Order(
    @PrimaryKey(autoGenerate = true) val orderId: Int? = null,
    val userCommandId: Int?,
    val productList: List<Product> = listOf<Product>(),
    val quantity: Int = 0,
    var deliveryDate: Date?= null,
    val commandDate: Date? = null,
    val fullPrice: Double? = null,
    val deliveryAddress: Address? = null
    )
