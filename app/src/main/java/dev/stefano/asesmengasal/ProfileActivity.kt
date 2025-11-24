package dev.stefano.asesmengasal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity : BaseActivity() {

    private lateinit var profileImage: CircleImageView

    private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = result.data?.data
            profileImage.setImageURI(imageUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChildContent(R.layout.activity_profile)

        currentNavId = R.id.profile_btn
        profileButton.isSelected = true
        setToolbarTitle("Profile")

        profileImage = findViewById(R.id.profile_image)

        profileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            pickImage.launch(intent)
        }

        val editProfileButton = findViewById<Button>(R.id.edit_profile_button)
        editProfileButton.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
    }
}