package com.example.spizeur.models

import android.icu.text.CaseMap.Title
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product (
    @PrimaryKey(autoGenerate = true) val productId: Int? = null,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String?,
    val images: Array<String?>,
)