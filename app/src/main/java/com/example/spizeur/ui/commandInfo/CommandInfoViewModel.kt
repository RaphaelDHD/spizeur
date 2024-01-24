package com.example.spizeur.ui.commandInfo

import androidx.lifecycle.ViewModel
import com.example.spizeur.domain.UserRepository
import com.example.spizeur.models.Address
import com.example.spizeur.models.PaymentInformation

class CommandInfoViewModel : ViewModel()   {
    fun getUserPaymentInfo(): PaymentInformation? {
        return UserRepository.currentUserOrder.value?.paymentInformation
    }

    fun getUserAddress(): Address? {
        return UserRepository.currentUserOrder.value?.deliveryAddress
    }

}