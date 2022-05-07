package com.example.kltn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.kltn.Fragment.RoomFragment
import com.google.android.material.navigation.NavigationView

class Room : AppCompatActivity() {
    private lateinit var drawer: DrawerLayout
    private lateinit var menu: NavigationView
    private lateinit var fragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        menu = findViewById(R.id.roomMenu)
        drawer = findViewById(R.id.mainDraw)
        fragment = RoomFragment("Living Room")
        supportFragmentManager.beginTransaction().replace(R.id.navRoom, fragment).commit()
        menu.setNavigationItemSelectedListener {
            selectedItem(it)
        }
    }

    private fun selectedItem(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.living -> {
                fragment = RoomFragment("Living Room")
            }
            R.id.kitchen -> {
                fragment = RoomFragment("Kitchen")
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.navRoom, fragment).commit()
        drawer.closeDrawer(menu)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (drawer.isDrawerOpen(menu)) drawer.closeDrawer(menu)
                else drawer.openDrawer(menu)
            }
            R.id.newRemote -> {
                startActivity(Intent(this,com.example.kltn.Menu::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}