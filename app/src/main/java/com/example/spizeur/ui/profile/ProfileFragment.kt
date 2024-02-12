package com.example.spizeur.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.spizeur.R
import com.example.spizeur.databinding.FragmentProfileBinding
import com.example.spizeur.domain.UserRepository
import com.example.spizeur.ui.login.LoginActivity
import com.example.spizeur.ui.settings.SettingsActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

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
        }
        view.findViewById<LinearLayout>(R.id.profile_logout_container)?.setOnClickListener {

            UserRepository.disconnectUserFromSharedPreference(requireContext(), UserRepository.currentUser.value?.email!!)
            UserRepository.logout()
            val intent = Intent(this.context,LoginActivity::class.java)
            startActivity(intent)

        }

    }

    override fun onResume() {
        super.onResume()

        // TextView Username
        var currentUsername : String? = UserRepository.currentUser.value?.username
        if (currentUsername.isNullOrEmpty())
        {
            currentUsername = "USERNAME"
        }
        view?.findViewById<TextView>(R.id.profile_username)?.text = currentUsername

        // TextView Email
        view?.findViewById<TextView>(R.id.profile_email)?.text = UserRepository.currentUser.value?.email

    }


}