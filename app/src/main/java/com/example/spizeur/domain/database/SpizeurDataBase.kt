package com.example.spizeur.domain.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.spizeur.domain.database.dao.ProductDAO
import com.example.spizeur.domain.database.dao.UserDAO
import com.example.spizeur.models.Product
import com.example.spizeur.models.User

@Database(entities = [User::class, Product::class], version = 1)
@TypeConverters(Converters::class)
abstract class SpizeurDataBase: RoomDatabase() {

    abstract fun userDAO(): UserDAO
    abstract fun productDAO(): ProductDAO

    companion object {
        private lateinit var instance: SpizeurDataBase

        fun initDatabase(context: Context) {
            instance = Room.databaseBuilder(
                context, SpizeurDataBase::class.java,
                "spizeur-db"
            )
                .build()
        }

        fun getInstance(): SpizeurDataBase {
            return instance
        }
    }

}