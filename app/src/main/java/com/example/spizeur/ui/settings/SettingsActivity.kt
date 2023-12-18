package com.example.spizeur.ui.settings

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.spizeur.R
import com.example.spizeur.domain.UserRepository

class SettingsActivity : AppCompatActivity() {

    private lateinit var viewModel: SettingsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[SettingsViewModel::class.java]

        setContentView(R.layout.activity_settings)

        findViewById<ImageView>(R.id.settings_return_arrow)?.setOnClickListener {
            finish()
        }

        findViewById<TextView>(R.id.text_change_username)?.setOnClickListener {
            showModifyDialogBox()
        }
    }

    private fun showModifyDialogBox() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_box_change_username)

        val cancelButton = dialog.findViewById<Button>(R.id.dialog_button_username_cancel)
        val validateButton = dialog.findViewById<Button>(R.id.dialog_button_username_validate)

        val editText = dialog.findViewById<EditText>(R.id.dialog_username_input)

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        validateButton.setOnClickListener {
            val newUsername = editText.text.toString()

            UserRepository.currentUser.value?.userId?.let {id ->
                viewModel.setUsername(newUsername, id)
            }

            // TODO : Toast validation changement username ?
            dialog.dismiss()
        }

        dialog.show()
    }

}