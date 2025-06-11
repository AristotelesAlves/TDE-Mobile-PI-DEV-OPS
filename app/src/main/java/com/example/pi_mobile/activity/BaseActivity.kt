package com.example.pi_mobile.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.pi_mobile.R
import com.example.pi_mobile.activity.HomeActivity
import com.example.pi_mobile.activity.NewService
import com.example.pi_mobile.activity.ProfilerActivity
import com.google.android.material.navigation.NavigationView

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var menuButton: ImageButton
    private var isDrawerOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun setupDrawer(drawerLayout: DrawerLayout, menuButton: ImageButton, navigationView: NavigationView) {
        this.drawerLayout = drawerLayout
        this.menuButton = menuButton

        menuButton.setOnClickListener {
            if (isDrawerOpen) drawerLayout.closeDrawer(GravityCompat.START)
            else drawerLayout.openDrawer(GravityCompat.START)
        }

        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}
            override fun onDrawerOpened(drawerView: View) {
                menuButton.setImageResource(R.drawable.ic_close)
                isDrawerOpen = true
            }

            override fun onDrawerClosed(drawerView: View) {
                menuButton.setImageResource(R.drawable.ic_menu_hamb)
                isDrawerOpen = false
            }

            override fun onDrawerStateChanged(newState: Int) {}
        })

        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> startActivity(Intent(this, HomeActivity::class.java))
                R.id.nav_services -> startActivity(Intent(this, NewService::class.java))
                R.id.nav_config -> startActivity(Intent(this, ProfilerActivity::class.java))
                R.id.nav_logout -> Toast.makeText(this, "Saindo...", Toast.LENGTH_SHORT).show()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
}
