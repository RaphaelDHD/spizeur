package com.example.spizeur.models

import java.util.Date

data class User(
    val Id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: Date,
    val address: Address,
    val paymentInformation: PaymentInformation,
    var favorite: Array<Product>,
    var historicOfOrder: Array<Order>
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
        if (!favorite.contentEquals(other.favorite)) return false
        if (!historicOfOrder.contentEquals(other.historicOfOrder)) return false

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
        result = 31 * result + favorite.contentHashCode()
        result = 31 * result + historicOfOrder.contentHashCode()
        return result
    }

}
