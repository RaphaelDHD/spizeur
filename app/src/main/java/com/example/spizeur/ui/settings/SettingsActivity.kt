package com.example.spizeur.ui.settings

import android.annotation.SuppressLint
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
import com.google.android.material.imageview.ShapeableImageView

class SettingsActivity : AppCompatActivity() {

    private lateinit var viewModel: SettingsViewModel
    private lateinit var dialogBox: Dialog

    @SuppressLint("MissingInflatedId")
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

        findViewById<TextView>(R.id.text_change_password)?.setOnClickListener {
            showModifyPasswordDialogBox()
        }

        findViewById<TextView>(R.id.text_change_profile_pic)?.setOnClickListener {
            // TODO : fonction profile pic avec autorisation
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
                UserRepository.registerUserToSharedPreferences(this, editTextEmailValue)
                dialogBox.dismiss()
            }
        }

        cancelButton.setOnClickListener {
            dialogBox.dismiss()
        }

        dialogBox.show()
    }

    private fun showModifyPasswordDialogBox() {
        dialogBox.setContentView(R.layout.dialog_box_change_password)

        val cancelButton = dialogBox.findViewById<Button>(R.id.dialog_button_password_cancel)
        val validateButton = dialogBox.findViewById<Button>(R.id.dialog_button_password_validate)

        val oldPasswordErrorTextView = dialogBox.findViewById<TextView>(R.id.dialog_old_password_error_message)
        val confirmPasswordErrorTextView = dialogBox.findViewById<TextView>(R.id.dialog_confirm_new_password_error_message)

        validateButton.setOnClickListener {
            val oldPasswordInput = dialogBox.findViewById<EditText>(R.id.dialog_old_password_input).text.toString()
            val newPasswordInput =  dialogBox.findViewById<EditText>(R.id.dialog_new_password_input).text.toString()
            val confirmNewPasswordInput =  dialogBox.findViewById<EditText>(R.id.dialog_confirm_new_password_input).text.toString()

            if (oldPasswordInput != UserRepository.currentUser.value?.password)
            {
                oldPasswordErrorTextView.visibility = View.VISIBLE
                Toast.makeText(this, "The old password is not correct", Toast.LENGTH_SHORT).show()
            }
            else {
                oldPasswordErrorTextView.visibility = View.INVISIBLE
            }

            if (newPasswordInput != confirmNewPasswordInput || newPasswordInput.isNullOrEmpty())
            {
                confirmPasswordErrorTextView.visibility = View.VISIBLE
                Toast.makeText(this, "Confirm password does not match the new password", Toast.LENGTH_SHORT).show()
            }
            else
            {
                confirmPasswordErrorTextView.visibility = View.INVISIBLE
                UserRepository.currentUser.value?.userId?.let {id ->
                    viewModel.setPassword(confirmNewPasswordInput, id)
                }
                dialogBox.dismiss()
            }
        }

        cancelButton.setOnClickListener {
            dialogBox.dismiss()
        }

        dialogBox.show()
    }

    private fun showModifyProfilePicDialogBox() {
        dialogBox.setContentView(R.layout.dialog_box_change_pic)

        val cancelButton = dialogBox.findViewById<Button>(R.id.dialog_button_pic_cancel)

        var currentImage = findViewById<ShapeableImageView>(R.id.dialog_box_pic_input)

        cancelButton.setOnClickListener {
            dialogBox.dismiss()
        }

        dialogBox.show()
    }

}