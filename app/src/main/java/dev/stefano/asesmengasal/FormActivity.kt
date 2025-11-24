package dev.stefano.asesmengasal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FormActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChildContent(R.layout.activity_form)

        currentNavId = R.id.nav_form
        navigationView.setCheckedItem(R.id.nav_form)
        setToolbarTitle("Formulir")

        // --- Dropdown Agama ---
        val religions = resources.getStringArray(R.array.religions)
        val religionAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, religions)
        val religionAutoCompleteTextView =
            findViewById<AutoCompleteTextView>(R.id.religion_auto_complete)
        religionAutoCompleteTextView.setAdapter(religionAdapter)

        religionAutoCompleteTextView.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        // --- Dropdown Hobi ---
        val hobbies = resources.getStringArray(R.array.hobbies)
        val hobbyAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, hobbies)
        val hobbyAutoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.hobby_auto_complete)
        hobbyAutoCompleteTextView.setAdapter(hobbyAdapter)

        hobbyAutoCompleteTextView.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        val submitButton = findViewById<Button>(R.id.submit_button)
        submitButton.setOnClickListener {
            if (validateInput()) {
                val name = findViewById<EditText>(R.id.name_edit_text).text.toString()
                val age = findViewById<EditText>(R.id.age_edit_text).text.toString()
                val genderId =
                    findViewById<RadioGroup>(R.id.gender_radio_group).checkedRadioButtonId
                val gender =
                    if (genderId != -1) findViewById<RadioButton>(genderId).text.toString() else ""
                val email = findViewById<EditText>(R.id.email_edit_text).text.toString()
                val religion =
                    findViewById<AutoCompleteTextView>(R.id.religion_auto_complete).text.toString()
                val hobby =
                    findViewById<AutoCompleteTextView>(R.id.hobby_auto_complete).text.toString()
                val address = findViewById<EditText>(R.id.address_edit_text).text.toString()

                val intent = Intent(this, OutputFormActivity::class.java).apply {
                    putExtra("name", name)
                    putExtra("age", age)
                    putExtra("gender", gender)
                    putExtra("email", email)
                    putExtra("religion", religion)
                    putExtra("hobby", hobby)
                    putExtra("address", address)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Harap isi semua kolom", Toast.LENGTH_SHORT).show()
            }
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

    private fun validateInput(): Boolean {
        val name = findViewById<EditText>(R.id.name_edit_text).text.toString()
        val age = findViewById<EditText>(R.id.age_edit_text).text.toString()
        val genderId = findViewById<RadioGroup>(R.id.gender_radio_group).checkedRadioButtonId
        val email = findViewById<EditText>(R.id.email_edit_text).text.toString()
        val religion =
            findViewById<AutoCompleteTextView>(R.id.religion_auto_complete).text.toString()
        val hobby = findViewById<AutoCompleteTextView>(R.id.hobby_auto_complete).text.toString()
        val address = findViewById<EditText>(R.id.address_edit_text).text.toString()

        return name.isNotEmpty() &&
                age.isNotEmpty() &&
                genderId != -1 &&
                email.isNotEmpty() &&
                religion.isNotEmpty() &&
                hobby.isNotEmpty() &&
                address.isNotEmpty()
    }
}