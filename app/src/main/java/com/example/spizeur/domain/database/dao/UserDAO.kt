package com.example.spizeur.domain.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.spizeur.models.User

@Dao
interface UserDAO {

    @Insert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("Select * from User")
    suspend fun getAll(): List<User>

    @Query("Select * from User where email=:email limit 1")
    suspend fun getUserFromMail(email: String): User


}