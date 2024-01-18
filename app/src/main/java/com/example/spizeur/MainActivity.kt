package com.example.spizeur

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.spizeur.R
import com.example.spizeur.databinding.ActivityMainBinding
import com.example.spizeur.ui.home.HomeViewModel
import com.example.spizeur.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

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

        // do not show the name of the fragment in the top left corner of the app
        supportActionBar?.setDisplayShowTitleEnabled(false)


/*        findViewById<Toolbar>(R.id.toolbar)
            .setupWithNavController(navController, appBarConfiguration)*/

        navView.setupWithNavController(navController)
    }

}