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

    @Query("Select * from User where email=:email limit 1")
    suspend fun getUser(email: String): User

    @Query("Select * from User")
    suspend fun getAllUser(): List<User>

    @Query("Select * from User where email=:email limit 1")
    suspend fun getUserFromMail(email: String): User

    @Query("UPDATE User SET username =:username WHERE Id = 1")
    suspend fun setUserNewUsername(username: String)

    @Query("UPDATE User SET email =:email WHERE Id = 1")
    suspend fun setUserNewEmail(email: String)
}