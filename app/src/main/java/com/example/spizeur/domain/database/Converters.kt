package com.example.spizeur.domain.database

import androidx.room.TypeConverter
import com.example.spizeur.domain.database.DBDataSource.getProductListByIds
import com.example.spizeur.models.Address
import com.example.spizeur.models.PaymentInformation
import com.example.spizeur.models.Product
import kotlinx.coroutines.runBlocking
import java.util.Date

class Converters {

    // Date
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    // Address
    @TypeConverter
    fun fromAddress(address: Address?): String {
        return address?.let {
            "${it.address},${it.city},${it.postalCode},${it.supplement}"
        } ?: ""
    }

    @TypeConverter
    fun toAddress(value: String?): Address? {
        return if (value.isNullOrBlank()) {
            null
        } else {
            val parts = value.split(",")
            Address(
                address = parts.getOrElse(0) { "" },
                city = parts.getOrElse(1) { "" },
                postalCode = parts.getOrElse(2) { "" },
                supplement = parts.getOrElse(3) { "" }
            )
        }
    }

    // paymentInformation
    @TypeConverter
    fun fromPaymentInformation(paymentInfo: PaymentInformation?): String {
        return paymentInfo?.let {
            "${it.number},${it.expireDate},${it.code}"
        } ?: ""
    }

    @TypeConverter
    fun toPaymentInformation(value: String?): PaymentInformation? {
        if (value.isNullOrBlank() || value.equals("null", ignoreCase = true)) {
            return null
        } else {
            val parts = value.split(",")
            return try {
                PaymentInformation(
                    number = parts.getOrElse(0) { "0" }?.toInt() ?: 0,
                    expireDate = parts.getOrElse(1) { "" },
                    code = parts.getOrElse(2) { "0" }?.toInt() ?: 0
                )
            } catch (e: NumberFormatException) {
                null
            }
        }
    }


    @TypeConverter
    fun fromImages(images : Array<String>): String {
        return images.joinToString(",")
    }

    @TypeConverter
    fun toImages(value: String): Array<String> {
        return value.split(",").toTypedArray()
    }

    @TypeConverter
    fun fromProductList(productList: List<Product>?): String? {
        if (productList == null) {
            return null
        }
        val productIds = productList.map { it.id }
        return productIds.joinToString(",")
    }
    @TypeConverter
    fun toProductList(productIds: String?): List<Product>? {
        if (productIds.isNullOrBlank()) {
            return null
        }
        val productIdList = productIds.split(",").map { it.toInt() }
        val productList = runBlocking {getProductListByIds(productIdList)}

        return productList
    }



}