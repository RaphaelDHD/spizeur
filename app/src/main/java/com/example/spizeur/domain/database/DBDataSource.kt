package com.example.spizeur.domain.database

import com.example.spizeur.models.User

object DBDataSource {

    suspend fun insertUser(user: User) {
        SpizeurDataBase.getInstance().userDAO().insertUser(user)
    }

}