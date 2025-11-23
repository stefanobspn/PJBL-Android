package dev.stefano.asesmengasal

import android.content.Intent
import android.os.Bundle
import com.google.android.material.card.MaterialCardView

class DashboardActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChildContent(R.layout.activity_dashboard)

        currentNavId = R.id.nav_dashboard
        navigationView.setCheckedItem(R.id.nav_dashboard)
        setToolbarTitle("Dashboard")

        findViewById<MaterialCardView>(R.id.calculator_card).setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.form_card).setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cashier_card).setOnClickListener {
            startActivity(Intent(this, CashierActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.temp_card).setOnClickListener {
            startActivity(Intent(this, TempActivity::class.java))
        }
    }
}