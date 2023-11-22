package com.example.spizeur.domain.database

import androidx.room.TypeConverter
import com.example.spizeur.models.Address
import com.example.spizeur.models.PaymentInformation
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
    fun fromAddress(address: Address): String {
        return "${address.address},${address.city},${address.postalCode},${address.supplement}"
    }

    @TypeConverter
    fun toAddress(value: String): Address {
        val parts = value.split(",")
        return Address(
            address = parts.getOrElse(0) { "" },
            city = parts.getOrElse(1) { "" },
            postalCode = parts.getOrElse(2) { "" },
            supplement = parts.getOrElse(3) { "" }
        )
    }

    // paymentInformation
    @TypeConverter
    fun fromPaymentInformation(paymentInfo: PaymentInformation): String {
        return "${paymentInfo.number},${paymentInfo.expireDate},${paymentInfo.code}"
    }

    @TypeConverter
    fun toPaymentInformation(value: String): PaymentInformation {
        val parts = value.split(",")
        return PaymentInformation(
            number = parts.getOrElse(0) { "0" }.toInt(),
            expireDate = parts.getOrElse(1) { "" },
            code = parts.getOrElse(2) { "0" }.toInt()
        )
    }

    @TypeConverter
    fun fromImages(images : Array<String>): String {
        return images.joinToString(",")
    }

    @TypeConverter
    fun toImages(value: String): Array<String> {
        return value.split(",").toTypedArray()
    }


}