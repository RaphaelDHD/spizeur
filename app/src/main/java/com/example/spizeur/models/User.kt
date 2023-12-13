package com.example.spizeur.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int? = null,
    val username: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String,
    val password: String,
    val birthDate: Date? = null,
    val address: Address? = null,
    val paymentInformation: PaymentInformation? = null
    ) {

}
