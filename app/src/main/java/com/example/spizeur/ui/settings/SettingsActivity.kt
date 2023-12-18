package com.example.spizeur.ui.settings

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.spizeur.R
import com.example.spizeur.domain.UserRepository

class SettingsActivity : AppCompatActivity() {

    private lateinit var viewModel: SettingsViewModel
    private lateinit var dialogBox: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogBox = Dialog(this)

        viewModel = ViewModelProvider(this)[SettingsViewModel::class.java]

        setContentView(R.layout.activity_settings)

        findViewById<ImageView>(R.id.settings_return_arrow)?.setOnClickListener {
            finish()
        }

        findViewById<TextView>(R.id.text_change_username)?.setOnClickListener {
            showModifyUsernameDialogBox()
        }

        findViewById<TextView>(R.id.text_change_email)?.setOnClickListener {
            showModifyEmailDialogBox()
        }
    }

    private fun showModifyUsernameDialogBox() {
        dialogBox.setContentView(R.layout.dialog_box_change_username)

        val cancelButton = dialogBox.findViewById<Button>(R.id.dialog_button_username_cancel)
        val validateButton = dialogBox.findViewById<Button>(R.id.dialog_button_username_validate)

        val editTextUsername = dialogBox.findViewById<EditText>(R.id.dialog_username_input)

        cancelButton.setOnClickListener {
            dialogBox.dismiss()
        }

        validateButton.setOnClickListener {
            val newUsername = editTextUsername.text.toString()

            UserRepository.currentUser.value?.userId?.let {id ->
                viewModel.setUsername(newUsername, id)
            }
            dialogBox.dismiss()
        }
        dialogBox.show()
    }

    private fun showModifyEmailDialogBox() {
        dialogBox.setContentView(R.layout.dialog_box_change_email)

        val cancelButton = dialogBox.findViewById<Button>(R.id.dialog_button_email_cancel)
        val validateButton = dialogBox.findViewById<Button>(R.id.dialog_button_email_validate)

        val textViewEmailError = dialogBox.findViewById<TextView>(R.id.dialog_email_error_message)

        validateButton.setOnClickListener {
            val editTextEmailValue = dialogBox.findViewById<EditText>(R.id.dialog_email_input).text.toString()
            val emailValidInput =  Patterns.EMAIL_ADDRESS.matcher(editTextEmailValue).matches()

            if (!emailValidInput)
            {
                textViewEmailError.visibility = View.VISIBLE
                Toast.makeText(this, "Veuillez d'abord entrer un mail valide ou annuler", Toast.LENGTH_SHORT).show()
            }
            else
            {
                textViewEmailError.visibility = View.INVISIBLE
                UserRepository.currentUser.value?.userId?.let {id ->
                    viewModel.setEmail(editTextEmailValue, id)
                }
                dialogBox.dismiss()
            }
        }

        cancelButton.setOnClickListener {
            dialogBox.dismiss()
        }

        dialogBox.show()
    }

}