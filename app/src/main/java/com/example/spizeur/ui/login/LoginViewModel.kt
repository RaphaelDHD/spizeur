package com.example.spizeur.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.spizeur.domain.UserRepository

class LoginViewModel: ViewModel() {

    suspend fun createAccount(username : String, email: String, password: String, confirmPassword: String) : Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")

        if (!email.matches(emailRegex)) {
            return false
        }

        if (password != confirmPassword) {
            return false
        }

        if (UserRepository.userExist(email)) {
            return false
        }
        UserRepository.createAccount(username, email, password)
        return true
    }
}