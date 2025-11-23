package dev.stefano.asesmengasal

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChildContent(R.layout.activity_dashboard)

        currentNavId = R.id.nav_dashboard
        navigationView.setCheckedItem(R.id.nav_dashboard)
        setToolbarTitle("Dashboard")
    }
}