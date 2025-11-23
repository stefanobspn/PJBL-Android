package dev.stefano.asesmengasal

import android.os.Bundle

class FormActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChildContent(R.layout.activity_form)

        currentNavId = R.id.nav_form
        navigationView.setCheckedItem(R.id.nav_form)
        setToolbarTitle("Formulir")
    }
}