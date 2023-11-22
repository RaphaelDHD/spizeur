package com.example.spizeur.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val Id: Int = 0,
    val username: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String,
    val password: String,
    val birthDate: Date? = null,
    val address: Address? = null,
    val paymentInformation: PaymentInformation? = null
    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (Id != other.Id) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (email != other.email) return false
        if (birthDate != other.birthDate) return false
        if (address != other.address) return false
        if (paymentInformation != other.paymentInformation) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Id
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + birthDate.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + paymentInformation.hashCode()
        return result
    }

}
