package dev.stefano.asesmengasal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import java.text.NumberFormat
import java.util.Locale

class CashierActivity : BaseActivity() {

    private lateinit var itemsContainer: LinearLayout
    private lateinit var emptyView: TextView
    private lateinit var itemsScrollView: ScrollView

    private val items = mutableListOf<Triple<String, Double, Int>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChildContent(R.layout.activity_cashier)

        currentNavId = R.id.nav_cashier
        navigationView.setCheckedItem(R.id.nav_cashier)
        setToolbarTitle("Kasir")

        itemsContainer = findViewById(R.id.items_container)
        emptyView = findViewById(R.id.empty_view)
        itemsScrollView = findViewById(R.id.items_scrollview)

        findViewById<Button>(R.id.add_item_button).setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            startActivityForResult(intent, ADD_ITEM_REQUEST_CODE)
        }

        findViewById<Button>(R.id.clear_all_button).setOnClickListener {
            items.clear()
            updateItemsView()
        }

        findViewById<Button>(R.id.print_receipt_button).setOnClickListener {
            if (items.isEmpty()) {
                Toast.makeText(this, "Tidak ada item untuk dicetak", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, PrintReceiptActivity::class.java)
                intent.putExtra("items_text", generateReceiptText())
                startActivity(intent)
            }
        }

        updateItemsView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                val name = it.getStringExtra("name") ?: ""
                val price = it.getDoubleExtra("price", 0.0)
                val quantity = it.getIntExtra("quantity", 0)
                items.add(Triple(name, price, quantity))
                updateItemsView()
            }
        }
    }

    private fun updateItemsView() {
        if (items.isEmpty()) {
            emptyView.visibility = View.VISIBLE
            itemsScrollView.visibility = View.GONE
        } else {
            emptyView.visibility = View.GONE
            itemsScrollView.visibility = View.VISIBLE

            itemsContainer.removeAllViews()
            val inflater = LayoutInflater.from(this)
            val numberFormat = NumberFormat.getNumberInstance(Locale("in", "ID"))
            for (item in items) {
                val itemView = inflater.inflate(R.layout.cashier_item_row, itemsContainer, false)
                val itemNameTextView = itemView.findViewById<TextView>(R.id.item_name_textview)
                val itemQuantityTextView = itemView.findViewById<TextView>(R.id.item_quantity_textview)
                val itemPriceTextView = itemView.findViewById<TextView>(R.id.item_price_textview)

                itemNameTextView.text = item.first
                itemQuantityTextView.text = "x${item.third}"
                val total = item.second * item.third
                itemPriceTextView.text = "Rp${numberFormat.format(total)}"

                itemsContainer.addView(itemView)
            }
        }
    }

    private fun generateReceiptText(): String {
        if (items.isEmpty()) {
            return "Belum ada item."
        }

        val receipt = StringBuilder("")
        var total = 0.0
        val numberFormat = NumberFormat.getNumberInstance(Locale("in", "ID"))
        for (item in items) {
            val itemTotal = item.second * item.third
            receipt.append("- ${item.first} x${item.third} Rp${numberFormat.format(itemTotal)}\n")
            total += itemTotal
        }
        receipt.append("\nTotal: Rp${numberFormat.format(total)}")
        return receipt.toString()
    }

    companion object {
        private const val ADD_ITEM_REQUEST_CODE = 1
    }
}