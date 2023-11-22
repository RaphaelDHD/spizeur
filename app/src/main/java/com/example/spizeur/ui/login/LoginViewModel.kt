package com.example.spizeur.ui.login

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spizeur.domain.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private var _isConnected: MutableLiveData<Boolean?> = MutableLiveData<Boolean?>(null)
    val isConnected: LiveData<Boolean?> = _isConnected
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
        var userExist = UserRepository.userExist(email)
        return userExist
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val userExist = async { UserRepository.userExist(email) }
            if (userExist.await()) {
                _isConnected.postValue(UserRepository.login(email, password))
            } else {
                _isConnected.postValue(false)
            }
        }
    }

}