package com.example.spizeur.models

import android.icu.text.CaseMap.Title
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (price != other.price) return false
        if (discountPercentage != other.discountPercentage) return false
        if (rating != other.rating) return false
        if (stock != other.stock) return false
        if (brand != other.brand) return false
        if (category != other.category) return false
        if (thumbnail != other.thumbnail) return false
        if (!images.contentEquals(other.images)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + discountPercentage.hashCode()
        result = 31 * result + rating.hashCode()
        result = 31 * result + stock
        result = 31 * result + brand.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + (thumbnail?.hashCode() ?: 0)
        result = 31 * result + images.contentHashCode()
        return result
    }
}