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
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.spizeur.R
import com.example.spizeur.databinding.FragmentLoginBinding
import com.example.spizeur.ui.home.HomeActiviy
import com.google.android.material.button.MaterialButton

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[LoginViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        vm.checkUserIsLogin(requireContext())
        vm.isConnected.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected != null && isConnected) {
                val intent = Intent(requireContext(), HomeActiviy::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else if(isConnected != null && !isConnected) {
                Toast.makeText(context, "Error, it's impossible to connect \n check your email and password", Toast.LENGTH_SHORT).show()
            }
        }

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.forgot_password_link)?.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.forgotpassword_fragment_graph)
        }

        view.findViewById<TextView>(R.id.signin_link)?.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.signin_fragment_graph)
        }

        view.findViewById<MaterialButton>(R.id.login_button)?.setOnClickListener {
            val email = view.findViewById<TextView>(R.id.email_login_input).text.toString()
            val password = view.findViewById<TextView>(R.id.password_login_input).text.toString()
            login(email, password)
        }
    }

    fun login(email: String, password: String) {
        vm.login(email, password, requireContext())
    }




}