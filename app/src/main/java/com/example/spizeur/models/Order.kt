package com.example.spizeur.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
class Order (
    @PrimaryKey(autoGenerate = true) val orderId: Int,
    val productList: List<Product>,
    val quantity: Int,
    var deliveryDate: Date,
    val commandDate: Date,
    val fullPrice: Double,
    val deliveryAddress: Address
    )
