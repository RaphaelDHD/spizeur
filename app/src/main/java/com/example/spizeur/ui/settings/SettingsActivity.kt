package com.example.spizeur.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import com.example.spizeur.R
import com.google.android.material.button.MaterialButton

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        findViewById<ImageView>(R.id.settings_return_arrow)?.setOnClickListener {
            //it.findNavController().popBackStack(R.id.nav_home_fragment, true)
            // TODO Faire retour en arriere de Settings à Home/profile.
        }
    }


}