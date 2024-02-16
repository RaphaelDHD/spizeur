package com.example.spizeur

import android.app.Application
import com.example.spizeur.domain.database.SpizeurDataBase
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import timber.log.Timber

class SpizeurApp: Application() {

    private lateinit var signInRequest: BeginSignInRequest

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        SpizeurDataBase.initDatabase(context = applicationContext)

        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                // Your server's client ID, not your Android client ID.
                .setServerClientId(getString(R.string.default_web_client_id))
                // Only show accounts previously used to sign in.
                .setFilterByAuthorizedAccounts(true)
                .build())
            .build();
    }

}