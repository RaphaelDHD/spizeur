package com.example.spizeur.ui.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spizeur.domain.UserRepository
import com.example.spizeur.models.User
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {

    fun setUsername(username: String, userId: Int)
    {
        viewModelScope.launch {
            try {
                UserRepository.setUserNewUsername(username, userId)

                val oldUser: User? = UserRepository.currentUser.value
                val newUser = User(
                    username = username,
                    userId = oldUser?.userId,
                    firstName = oldUser?.firstName,
                    lastName = oldUser?.lastName,
                    email = oldUser!!.email,
                    password = oldUser.password,
                    birthDate = oldUser.birthDate,
                    address = oldUser.address,
                    paymentInformation = oldUser.paymentInformation
                )
                UserRepository.setCurrentUser(newUser)
            }
            catch (error: Exception)
            {
                Log.e("ERROR_SET_USERNAME", error.message.toString())
            }

        }
    }

    fun setEmail(email: String, userId: Int)
    {
        viewModelScope.launch {
            try {
                UserRepository.setUserNewEmail(email, userId)

                val oldUser: User? = UserRepository.currentUser.value
                val newUser = User(
                    username = oldUser!!.username,
                    userId = oldUser?.userId,
                    firstName = oldUser?.firstName,
                    lastName = oldUser?.lastName,
                    email = email,
                    password = oldUser!!.password,
                    birthDate = oldUser.birthDate,
                    address = oldUser.address,
                    paymentInformation = oldUser.paymentInformation
                )
                UserRepository.setCurrentUser(newUser)
            } catch (error: Exception) {
                Log.e("ERROR_SET_EMAIL", error.message.toString())
            }
        }
    }

}