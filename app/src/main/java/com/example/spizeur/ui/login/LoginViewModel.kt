package com.example.spizeur.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spizeur.domain.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    fun createAccount(username : String, email: String, password: String, confirmPassword: String) : Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")

        if (!email.matches(emailRegex)) {
            return false
        }

        if (password != confirmPassword) {
            return false
        }

        viewModelScope.launch {
            UserRepository.createAccount(username, email, password)
        }
        return true
    }

    suspend fun userExist(email: String): Boolean {
        var userExist = false
        userExist = UserRepository.userExist(email)
        return userExist
    }

}