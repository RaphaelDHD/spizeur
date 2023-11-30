package com.example.spizeur.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.spizeur.ProfileViewModel
import com.example.spizeur.R
import com.example.spizeur.databinding.FragmentProfileBinding
import com.example.spizeur.ui.home.HomeActiviy
import com.example.spizeur.ui.settings.SettingsActivity
import com.google.android.material.button.MaterialButton

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<LinearLayout>(R.id.profile_settings_container)?.setOnClickListener {
            val intent = Intent(this.context, SettingsActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }
}