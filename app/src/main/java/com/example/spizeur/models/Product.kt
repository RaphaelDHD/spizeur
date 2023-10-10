package com.example.spizeur.models

import android.icu.text.CaseMap.Title

class Product (
    val id: Int,
    var title: String,
    var description: String,
    var price: Double,
    var discountPercentage: Double,
    var rating: Double,
    var stock: Int,
    var brand: String,
    var category: String,
    var thumbnail: String,
    var images: Array<String>,
)