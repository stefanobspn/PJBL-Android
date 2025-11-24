package dev.stefano.asesmengasal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class OutputFormActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChildContent(R.layout.activity_output_form)
        setToolbarTitle("Hasil Form")

        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        val gender = intent.getStringExtra("gender")
        val email = intent.getStringExtra("email")
        val religion = intent.getStringExtra("religion")
        val hobby = intent.getStringExtra("hobby")
        val address = intent.getStringExtra("address")

        findViewById<TextView>(R.id.output_name_text).text = name
        findViewById<TextView>(R.id.output_age_text).text = age
        findViewById<TextView>(R.id.output_gender_text).text = gender
        findViewById<TextView>(R.id.output_email_text).text = email
        findViewById<TextView>(R.id.output_religion_text).text = religion
        findViewById<TextView>(R.id.output_hobby_text).text = hobby
        findViewById<TextView>(R.id.output_address_text).text = address

        val addToProfileButton = findViewById<Button>(R.id.add_to_profile_button)
        addToProfileButton.setOnClickListener {
            saveProfileData(name, age, gender, email, religion, hobby, address)
            val intent = Intent(this, ProfileActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        val closeButton = findViewById<Button>(R.id.close_button)
        closeButton.setOnClickListener {
            finish()
        }
    }

    private fun saveProfileData(
        name: String?,
        age: String?,
        gender: String?,
        email: String?,
        religion: String?,
        hobby: String?,
        address: String?
    ) {
        val sharedPref = getSharedPreferences("ProfilePrefs", MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putString("name", name)
        editor.putString("age", age)
        editor.putString("gender", gender)
        editor.putString("email", email)
        editor.putString("religion", religion)
        editor.putString("hobby", hobby)
        editor.putString("address", address)
        editor.putBoolean("isProfileCreated", true)

        editor.apply()
    }
}
