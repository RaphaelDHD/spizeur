package com.example.spizeur.domain.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.spizeur.models.User
import com.example.spizeur.models.crossRef.UserWithOrders

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
    
    @Transaction
    @Query("SELECT * FROM User where userId=:userId")
    fun getUserOrderById(userId: Int): UserWithOrders


    @Query("UPDATE User SET email =:email WHERE Id = 1")
    suspend fun setUserNewEmail(email: String)
}