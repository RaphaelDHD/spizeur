package com.example.spizeur.ui.commandInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spizeur.domain.UserRepository
import com.example.spizeur.models.Address
import com.example.spizeur.models.PaymentInformation
import kotlinx.coroutines.launch

class CommandInfoViewModel : ViewModel()   {
    fun getUserPaymentInfo(): PaymentInformation? {
        return UserRepository.currentUserOrder.value?.paymentInformation
    }

    fun getUserAddress(): Address? {
        return UserRepository.currentUserOrder.value?.deliveryAddress
    }

    fun command(address: Address, paymentInformation: PaymentInformation) {
        UserRepository.setCommandAddress(address)
        UserRepository.setCommandPaymentInformation(paymentInformation)
        viewModelScope.launch {
            UserRepository.command()
        }
    }

    fun getTotal() : Double {
        val products = UserRepository.currentUserOrder.value?.productList
        var total = 0.0
        if (products != null) {
            for (product in products) {
                total += product.price
            }
        }
        return total
    }


}