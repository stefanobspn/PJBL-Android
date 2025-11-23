package dev.stefano.asesmengasal

import android.os.Bundle

class CalculatorActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChildContent(R.layout.activity_calculator)

        currentNavId = R.id.nav_calculator
        navigationView.setCheckedItem(R.id.nav_calculator)
        setToolbarTitle("Kalkulator")
    }
}