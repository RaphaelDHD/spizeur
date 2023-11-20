package com.example.spizeur.domain

import android.app.Application
import timber.log.Timber

class Spizeur : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())



    }

}