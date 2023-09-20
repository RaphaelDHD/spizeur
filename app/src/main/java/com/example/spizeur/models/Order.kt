package com.example.spizeur.models

import java.util.Date

class Order (
    val product: Product,
    val quantity: Int,
    var deliveryDate: Date,
    val commandDate: Date,
    val fullPrice: Double,
    val deliveryAddress: Address
    )
