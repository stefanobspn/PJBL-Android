package dev.stefano.asesmengasal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddItemActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChildContent(R.layout.activity_add_item)

        setToolbarTitle("Tambah Item")

        val itemNameEditText = findViewById<EditText>(R.id.item_name_edit_text)
        val itemPriceEditText = findViewById<EditText>(R.id.item_price_edit_text)
        val itemQuantityEditText = findViewById<EditText>(R.id.item_quantity_edit_text)

        findViewById<Button>(R.id.add_button).setOnClickListener {
            val name = itemNameEditText.text.toString()
            val price = itemPriceEditText.text.toString().toDoubleOrNull() ?: 0.0
            val quantity = itemQuantityEditText.text.toString().toIntOrNull() ?: 0

            if (name.isNotEmpty() && price > 0 && quantity > 0) {
                val resultIntent = Intent()
                resultIntent.putExtra("name", name)
                resultIntent.putExtra("price", price)
                resultIntent.putExtra("quantity", quantity)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}