package com.example.spizeur.ui.commandInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.widget.RadioButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.spizeur.R
import com.example.spizeur.domain.UserRepository
import com.example.spizeur.ui.cart.CartViewModel

class CommandInfoActivity : AppCompatActivity() {
    private lateinit var vm: CommandInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command_info)
        vm = ViewModelProvider(this).get(CommandInfoViewModel::class.java)

        // address information
        val address = vm.getUserAddress()
        if (address == null) {
            findViewById<RadioButton>(R.id.YourAddressButton).isEnabled = false
            findViewById<RadioButton>(R.id.OtherAddressButton).isChecked = true
        }

        // payment information
        val paymentInfo = vm.getUserPaymentInfo()
        if (paymentInfo != null) {
            val cardNumber = paymentInfo.number
            val cardNumberLength = cardNumber.length
            val cardNumberLastFourDigits = cardNumber.substring(cardNumberLength - 4, cardNumberLength)
            findViewById<TextView>(R.id.RegisteredCardTextView).text = "**** **** **** $cardNumberLastFourDigits"
        } else {
            findViewById<TextView>(R.id.RegisteredCardTextView).text = "No card registered"
            findViewById<RadioButton>(R.id.RegisteredCardButton).isEnabled = false
            findViewById<RadioButton>(R.id.OtherCardButton).isChecked = true
        }


    }




}