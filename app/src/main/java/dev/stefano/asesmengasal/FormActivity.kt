package dev.stefano.asesmengasal

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
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
}
