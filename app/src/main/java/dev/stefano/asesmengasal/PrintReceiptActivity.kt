package dev.stefano.asesmengasal

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PrintReceiptActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChildContent(R.layout.activity_print_receipt)

        setToolbarTitle("Struk")

        val receiptTextView = findViewById<TextView>(R.id.receipt_textview)
        val dateTextView = findViewById<TextView>(R.id.date_textview)
        val timeTextView = findViewById<TextView>(R.id.time_textview)
        val closeButton = findViewById<Button>(R.id.close_button)

        val itemsText = intent.getStringExtra("items_text")
        val total = intent.getDoubleExtra("total", 0.0)

        receiptTextView.text = itemsText

        val sdf = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
        dateTextView.text = sdf.format(Date())

        val timeSdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        timeTextView.text = timeSdf.format(Date())

        closeButton.setOnClickListener {
            finish()
        }
    }
}