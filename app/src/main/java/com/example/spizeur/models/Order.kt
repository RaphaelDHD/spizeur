package com.example.spizeur.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
class Order(
    @PrimaryKey(autoGenerate = true) val orderId: Int? = null,
    val userCommandId: Int?,
    val productList: MutableList<Product> = mutableListOf<Product>(),
    var deliveryDate: Date?= null,
    var commandDate: Date? = null,
    var fullPrice: Double? = 0.0,
    var deliveryAddress: Address? = null,
    var paymentInformation: PaymentInformation? = null
    )
