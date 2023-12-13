package com.example.spizeur.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spizeur.domain.UserRepository
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {

    fun setUsername(username: String)
    {
        viewModelScope.launch {
            UserRepository.setUserNewUsername(username)
        }
    }

}