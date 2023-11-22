package com.example.spizeur.ui.login

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spizeur.domain.UserRepository
import com.example.spizeur.models.User
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private var _isConnected: MutableLiveData<Boolean?> = MutableLiveData<Boolean?>(null)
    val isConnected: LiveData<Boolean?> = _isConnected

    private var _currentUser: MutableLiveData<User?> = MutableLiveData<User?>(null)
    val currentUser: LiveData<User?> = _currentUser
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

    fun login(email: String, password: String, context: Context) {
        viewModelScope.launch {
            val userExist = async { UserRepository.userExist(email) }
            if (userExist.await()) {
                val loginResult = UserRepository.login(email, password)
                _isConnected.postValue(loginResult)
                if (loginResult) {
                    _currentUser.postValue(UserRepository.getUser(email))
                    UserRepository.registerUserToSharedPreferences(context,email)
                }
            } else {
                _isConnected.postValue(false)
            }
        }
    }

    fun checkUserIsLogin(context: Context) {
        val email = UserRepository.getUserFromSharedPreferences(context)
        if (email != null) {
            _isConnected.postValue(true)
            viewModelScope.launch {
                val user = UserRepository.getUser(email)
                _currentUser.postValue(user)
            }
        }
    }

}