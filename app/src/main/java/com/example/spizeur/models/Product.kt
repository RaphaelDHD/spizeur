package com.example.spizeur.models

class Product (
    val id: Int,
    var name: String,
    var description: String,
    var price: Double,
    var discountPercentage: Double,
    var brand: String,
    var category: String,
    var image: Array<String>,
    var Stock: Int
)