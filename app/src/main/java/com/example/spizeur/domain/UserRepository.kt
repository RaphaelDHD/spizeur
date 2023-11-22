package com.example.spizeur.domain

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.spizeur.domain.database.DBDataSource
import com.example.spizeur.models.User

object UserRepository {

        suspend fun login(email: String, password: String): Boolean {
            val user = DBDataSource.getUser(email)
            if (user.password == password) {
                return true
            }
            return false
        }

    suspend fun getUser(email: String): User {
        return DBDataSource.getUser(email)
    }

    fun registerUserToSharedPreferences(context: Context, email: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("SpizeurSharedPreference", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.apply()
    }

    fun getUserFromSharedPreferences(context: Context): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("SpizeurSharedPreference", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", null)
        return email
    }

        suspend fun userExist(email: String): Boolean {
            val User = DBDataSource.getUser(email)
            if (User != null) {
                return true
            }
            return false
        }


        suspend fun createAccount(username: String, email: String, password: String) {
            val user = User(username = username, email = email, password = password)
            DBDataSource.insertUser(user)
        }

        fun logout() {

        }
}