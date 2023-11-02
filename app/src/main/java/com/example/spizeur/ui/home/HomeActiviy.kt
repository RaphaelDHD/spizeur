package com.example.spizeur.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.spizeur.R
import com.example.spizeur.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeActiviy : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: HomeViewModel = HomeViewModel()

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

        navView.setupWithNavController(navController)
    }


}