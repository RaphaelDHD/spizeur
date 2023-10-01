package com.example.spizeur.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.spizeur.R
import com.example.spizeur.databinding.ActivityMainBinding
import com.example.spizeur.models.services.ApiClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigationContainer

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home_fragment, R.id.nav_profile_fragment
            )
        )

        executeCall()

        findViewById<Toolbar>(R.id.toolbar)
            .setupWithNavController(navController, appBarConfiguration)

//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun executeCall() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.apiService.getProductById(1)

                if (response.isSuccessful && response.body() != null) {

                    val content = response.body()
                    Log.e("API_TEST_CORRECT", content.toString())



                } else {
                    Log.e("API_TEST", "Reponse pas correcte")
                }

            } catch (e: Exception) {
                Log.e("API_TEST", "${e.message}")
            }
        }
    }



}