package com.example.spizeur.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.spizeur.R
import com.example.spizeur.databinding.FragmentLoginBinding
import com.example.spizeur.ui.home.HomeActiviy
import com.google.android.material.button.MaterialButton

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel = LoginViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view?.findViewById<TextView>(R.id.forgot_password_link)?.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.forgotpassword_fragment_graph)
        }

        view?.findViewById<TextView>(R.id.create_account_link)?.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.signin_fragment_graph)
        }

        view?.findViewById<MaterialButton>(R.id.login_button)?.setOnClickListener {
            val intent = Intent(this.context, HomeActiviy::class.java)
            startActivity(intent)
        }
    }

}