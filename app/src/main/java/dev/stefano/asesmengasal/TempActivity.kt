package dev.stefano.asesmengasal

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputEditText

class TempActivity : BaseActivity() {

    private lateinit var tempEditText: TextInputEditText
    private lateinit var tempUnitAutoComplete: AutoCompleteTextView
    private lateinit var celsiusOutputTv: TextView
    private lateinit var fahrenheitOutputTv: TextView
    private lateinit var kelvinOutputTv: TextView
    private lateinit var reamurOutputTv: TextView

    private companion object {
        const val SELECTED_UNIT_KEY = "selected_unit"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChildContent(R.layout.activity_temp)

        currentNavId = R.id.nav_temp
        navigationView.setCheckedItem(R.id.nav_temp)
        setToolbarTitle("Konversi Suhu")

        // Inisialisasi Views
        tempEditText = findViewById(R.id.temp_edit_text)
        tempUnitAutoComplete = findViewById(R.id.temp_unit_auto_complete)
        celsiusOutputTv = findViewById(R.id.celsius_output_tv)
        fahrenheitOutputTv = findViewById(R.id.fahrenheit_output_tv)
        kelvinOutputTv = findViewById(R.id.kelvin_output_tv)
        reamurOutputTv = findViewById(R.id.reamur_output_tv)

        // Setup Dropdown Unit
        val units = resources.getStringArray(R.array.temperature_units)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, units)
        tempUnitAutoComplete.setAdapter(adapter)

        // Restore state or set default
        val selectedUnit = savedInstanceState?.getString(SELECTED_UNIT_KEY)
        if (selectedUnit != null && units.contains(selectedUnit)) {
            tempUnitAutoComplete.setText(selectedUnit, false)
        } else {
            tempUnitAutoComplete.setText(units[0], false)
        }


        // Close keyboard on unit focus
        tempUnitAutoComplete.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        // Tambahkan Listener untuk konversi realtime
        tempEditText.doAfterTextChanged { convertAndDisplay() }
        tempUnitAutoComplete.setOnItemClickListener { _, _, _, _ -> convertAndDisplay() }

        // Lakukan konversi awal jika ada teks
        convertAndDisplay()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SELECTED_UNIT_KEY, tempUnitAutoComplete.text.toString())
    }

    private fun convertAndDisplay() {
        val inputText = tempEditText.text.toString()
        if (inputText.isEmpty() || inputText == "." || inputText == "-") {
            resetOutputFields()
            return
        }

        val inputValue = inputText.toDoubleOrNull() ?: 0.0
        val selectedUnit = tempUnitAutoComplete.text.toString()

        // Konversi nilai input ke Celcius sebagai basis
        val valueInCelsius = when (selectedUnit) {
            "Fahrenheit" -> (inputValue - 32) * 5 / 9
            "Kelvin" -> inputValue - 273.15
            "Reamur" -> inputValue * 5 / 4
            else -> inputValue // Default adalah Celcius
        }

        // Konversi dari Celcius ke unit lain
        val celsius = valueInCelsius
        val fahrenheit = valueInCelsius * 9 / 5 + 32
        val kelvin = valueInCelsius + 273.15
        val reamur = valueInCelsius * 4 / 5

        // Tampilkan hasil
        celsiusOutputTv.text = String.format("%.2f", celsius)
        fahrenheitOutputTv.text = String.format("%.2f", fahrenheit)
        kelvinOutputTv.text = String.format("%.2f", kelvin)
        reamurOutputTv.text = String.format("%.2f", reamur)
    }

    private fun resetOutputFields() {
        celsiusOutputTv.text = "-"
        fahrenheitOutputTv.text = "-"
        kelvinOutputTv.text = "-"
        reamurOutputTv.text = "-"
    }
}