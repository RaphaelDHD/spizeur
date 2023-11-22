package com.example.spizeur.domain

import com.example.spizeur.domain.database.DBDataSource
import com.example.spizeur.models.User

object UserRepository {

        fun login(email: String, password: String): Boolean {
            return true
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