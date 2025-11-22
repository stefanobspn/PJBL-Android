package dev.stefano.asesmengasal

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

open class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        val menuButton = findViewById<ImageButton>(R.id.menu_btn)
        val drawer = findViewById<DrawerLayout>(R.id.drawerLayout)

        menuButton.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }
    }

    fun setChildContent(layoutRes: Int) {
        val container = findViewById<FrameLayout>(R.id.contentFrame)
        layoutInflater.inflate(layoutRes, container, true)
    }
}