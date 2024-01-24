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

   /* fun command(address: Address, paymentInformation: PaymentInformation) {
        UserRepository.setCommandAddress(address)
        UserRepository.setCommandPaymentInformation(paymentInformation)
        viewModelScope.launch {
            UserRepository.command()
        }
    }
*/

}