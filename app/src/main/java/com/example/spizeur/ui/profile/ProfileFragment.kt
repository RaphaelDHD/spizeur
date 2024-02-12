package com.example.spizeur.ui.profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.spizeur.R
import com.example.spizeur.databinding.FragmentProfileBinding
import com.example.spizeur.domain.UserRepository
import com.example.spizeur.ui.settings.SettingsActivity
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {

    val REQUEST_IMAGE_OPEN = 1
    val REQUEST_STORAGE_PERMISSION=2
    lateinit var imagePicked: ShapeableImageView


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

        imagePicked = view.findViewById<ShapeableImageView>(R.id.profile_pic)
        imagePicked.setOnClickListener {
            Log.e("CLICK IMAGE", "CLICK IMAGE !")
            openImagePicker()
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

    private fun openImagePicker()
    {
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        if (ContextCompat.checkSelfPermission(requireContext(), permission) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_STORAGE_PERMISSION)
        } else {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                type = "image/*"
                addCategory(Intent.CATEGORY_OPENABLE)
            }
            startActivityForResult(intent, REQUEST_IMAGE_OPEN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_OPEN && resultCode == Activity.RESULT_OK) {
            val fullPhotoUri: Uri? = data?.data
            Log.i("REQUEST_IMAGE_OPEN", fullPhotoUri.toString())
            //createUser.avatar=fullPhotoUri.toString();
            Picasso.get()
                .load(fullPhotoUri) // Précisez le chemin du fichier avec le préfixe "file://"
                .into(imagePicked)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_STORAGE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openImagePicker()
                } else {
                    // La permission a été refusée, vous pouvez informer l'utilisateur ici
                    Toast.makeText(this.context, "Permission non accordée", Toast.LENGTH_LONG).show()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

}