package dev.stefano.asesmengasal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.card.MaterialCardView
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity : BaseActivity() {

    private lateinit var profileImage: CircleImageView
    private lateinit var noProfileText: TextView
    private lateinit var profileCard: MaterialCardView
    private lateinit var editProfileButton: Button



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
        noProfileText = findViewById(R.id.no_profile_text)
        profileCard = findViewById(R.id.profile_card)
        editProfileButton = findViewById(R.id.edit_profile_button)

        profileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            pickImage.launch(intent)
        }

        editProfileButton.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

        checkProfile()
    }

    private fun checkProfile() {
        val sharedPref = getSharedPreferences("ProfilePrefs", MODE_PRIVATE)
        val isProfileCreated = sharedPref.getBoolean("isProfileCreated", false)

        val params = editProfileButton.layoutParams as ConstraintLayout.LayoutParams
        if (isProfileCreated) {
            noProfileText.visibility = View.GONE
            profileCard.visibility = View.VISIBLE
            profileImage.visibility = View.VISIBLE
            params.topToBottom = R.id.profile_card
            loadProfileData()
        } else {
            noProfileText.visibility = View.VISIBLE
            profileCard.visibility = View.GONE
            profileImage.visibility = View.GONE
            params.topToBottom = R.id.no_profile_text
        }
        editProfileButton.layoutParams = params
    }

    private fun loadProfileData() {
        val sharedPref = getSharedPreferences("ProfilePrefs", MODE_PRIVATE)
        findViewById<TextView>(R.id.name_text_view).text = sharedPref.getString("name", "")
        findViewById<TextView>(R.id.age_text_view).text = sharedPref.getString("age", "")
        findViewById<TextView>(R.id.gender_text_view).text = sharedPref.getString("gender", "")
        findViewById<TextView>(R.id.email_text_view).text = sharedPref.getString("email", "")
        findViewById<TextView>(R.id.religion_text_view).text = sharedPref.getString("religion", "")
        findViewById<TextView>(R.id.hobby_text_view).text = sharedPref.getString("hobby", "")
        findViewById<TextView>(R.id.address_text_view).text = sharedPref.getString("address", "")

        val imageUriString = sharedPref.getString("profileImageUri", null)
        if (imageUriString != null) {
            profileImage.setImageURI(Uri.parse(imageUriString))
        }
    }
}
