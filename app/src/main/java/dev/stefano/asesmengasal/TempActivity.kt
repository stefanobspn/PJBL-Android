package dev.stefano.asesmengasal

import android.os.Bundle

class TempActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChildContent(R.layout.activity_temp)

        currentNavId = R.id.nav_temp
        navigationView.setCheckedItem(R.id.nav_temp)
        setToolbarTitle("Konversi Suhu")
    }
}