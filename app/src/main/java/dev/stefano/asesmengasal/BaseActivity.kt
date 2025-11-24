package dev.stefano.asesmengasal

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

open class BaseActivity : AppCompatActivity() {
    protected lateinit var navigationView: NavigationView
    protected lateinit var titleTextView: TextView
    private lateinit var drawer: DrawerLayout
    protected var currentNavId: Int = R.id.nav_dashboard
    protected lateinit var profileButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        enableEdgeToEdge()

        val menuButton = findViewById<ImageButton>(R.id.menu_btn)
        profileButton = findViewById<ImageButton>(R.id.profile_btn)
        drawer = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        titleTextView = findViewById(R.id.title_tv)

        menuButton.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }

        profileButton.setOnClickListener {
            if (currentNavId != R.id.profile_btn) {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_dashboard -> {
                    if (currentNavId != R.id.nav_dashboard) {
                        val intent = Intent(this, DashboardActivity::class.java)
                        startActivity(intent)
                    }
                }

                R.id.nav_calculator -> {
                    if (currentNavId != R.id.nav_calculator) {
                        val intent = Intent(this, CalculatorActivity::class.java)
                        startActivity(intent)
                    }
                }

                R.id.nav_form -> {
                    if (currentNavId != R.id.nav_form) {
                        val intent = Intent(this, FormActivity::class.java)
                        startActivity(intent)
                    }
                }

                R.id.nav_cashier -> {
                    if (currentNavId != R.id.nav_cashier) {
                        val intent = Intent(this, CashierActivity::class.java)
                        startActivity(intent)
                    }
                }

                R.id.nav_temp -> {
                    if (currentNavId != R.id.nav_temp) {
                        val intent = Intent(this, TempActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
            true
        }
    }

    fun setChildContent(layoutRes: Int) {
        val container = findViewById<FrameLayout>(R.id.contentFrame)
        layoutInflater.inflate(layoutRes, container, true)
    }

    fun setToolbarTitle(title: String) {
        titleTextView.text = title
    }
}