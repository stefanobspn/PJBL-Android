package dev.stefano.asesmengasal

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FormActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChildContent(R.layout.activity_form)

        currentNavId = R.id.nav_form
        navigationView.setCheckedItem(R.id.nav_form)
        setToolbarTitle("Formulir")

        // --- Dropdown Agama ---
        val religions = resources.getStringArray(R.array.religions)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, religions)
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.religion_auto_complete)
        autoCompleteTextView.setAdapter(adapter)

        autoCompleteTextView.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        val submitButton = findViewById<Button>(R.id.submit_button)
        submitButton.setOnClickListener {
            saveProfileData()
            finish()
        }

        // --- Logika untuk menghindari keyboard ---
        val rootView = findViewById<View>(android.R.id.content)
        val keyboardSpace = findViewById<View>(R.id.keyboard_space)

        ViewCompat.setOnApplyWindowInsetsListener(rootView) { _, insets ->
            val imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
            val imeHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom

            val layoutParams = keyboardSpace.layoutParams
            if (imeVisible) {
                layoutParams.height = imeHeight
            } else {
                layoutParams.height = 0
            }
            keyboardSpace.layoutParams = layoutParams

            insets
        }
    }

    private fun saveProfileData() {
        val sharedPref = getSharedPreferences("ProfilePrefs", MODE_PRIVATE)
        val editor = sharedPref.edit()

        val name = findViewById<EditText>(R.id.name_edit_text).text.toString()
        val age = findViewById<EditText>(R.id.age_edit_text).text.toString()
        val genderId = findViewById<RadioGroup>(R.id.gender_radio_group).checkedRadioButtonId
        val gender = if (genderId != -1) findViewById<RadioButton>(genderId).text.toString() else ""
        val email = findViewById<EditText>(R.id.email_edit_text).text.toString()
        val religion = findViewById<AutoCompleteTextView>(R.id.religion_auto_complete).text.toString()
        val hobby = findViewById<EditText>(R.id.hobby_edit_text).text.toString()
        val address = findViewById<EditText>(R.id.address_edit_text).text.toString()

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