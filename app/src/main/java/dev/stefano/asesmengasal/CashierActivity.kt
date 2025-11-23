package dev.stefano.asesmengasal

import android.os.Bundle

class CashierActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChildContent(R.layout.activity_cashier)

        currentNavId = R.id.nav_cashier
        navigationView.setCheckedItem(R.id.nav_cashier)
        setToolbarTitle("Kasir")
    }
}