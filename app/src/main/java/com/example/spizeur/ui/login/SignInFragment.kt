package com.example.spizeur.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.spizeur.R
import com.example.spizeur.databinding.FragmentSignInBinding
import com.example.spizeur.MainActivity
import kotlinx.coroutines.launch

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.signin_return_arrow)?.setOnClickListener {
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            fragmentManager.popBackStack()
        }

        view.findViewById<Button>(R.id.sign_in_button)?.setOnClickListener {
            val username = view.findViewById<EditText>(R.id.username_signin_input).text.toString()
            val email = view.findViewById<EditText>(R.id.email_signin_input).text.toString()
            val password = view.findViewById<EditText>(R.id.password_signin_set_input).text.toString()
            val confirmPassword = view.findViewById<EditText>(R.id.password_signin_confirm_input).text.toString()
            viewLifecycleOwner.lifecycleScope.launch {
                createAccount(username, email, password, confirmPassword)
            }
        }
    }

    private suspend fun createAccount(username: String, email: String, password: String, confirmPassword: String) {
        val canCreateAccount = !vm.userExist(email)
        if (canCreateAccount) {
            val success = vm.createAccount(username, email, password, confirmPassword)
            if (success) {
                vm.registerUserToSharedPreferences(requireContext(), email)
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                Toast.makeText(context, "Error, it's impossible to create your account", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Error, this email is already used", Toast.LENGTH_SHORT).show()
        }
    }



}