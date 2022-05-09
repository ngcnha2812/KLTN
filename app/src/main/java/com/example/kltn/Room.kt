package com.example.kltn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.kltn.Fragment.RoomFragment
import com.example.kltn.Model.RoomModel
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Room : AppCompatActivity() {
    private lateinit var drawer: DrawerLayout
    private lateinit var menu: NavigationView
    private lateinit var fragment: Fragment

    private var currentFragment = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        menu = findViewById(R.id.roomMenu)
        drawer = findViewById(R.id.mainDraw)
        fragment = RoomFragment("LivingRoom")
        currentFragment = "LivingRoom"
        supportFragmentManager.beginTransaction().replace(R.id.navRoom, fragment).commit()
        menu.setNavigationItemSelectedListener {
            selectedItem(it)
        }
    }

    private fun selectedItem(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.living -> {
                fragment = RoomFragment("LivingRoom")
                currentFragment = "LivingRoom"
            }
            R.id.kitchen -> {
                fragment = RoomFragment("Kitchen")
                currentFragment = "Kitchen"
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
                val intent = Intent(this,com.example.kltn.Menu::class.java)
                intent.putExtra("roomName",currentFragment.uppercase())
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}