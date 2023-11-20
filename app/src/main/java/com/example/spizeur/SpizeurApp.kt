package com.example.spizeur

import android.app.Application
import com.example.spizeur.domain.database.SpizeurDataBase
import timber.log.Timber

class SpizeurApp: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        SpizeurDataBase.initDatabase(context = applicationContext)
    }

}