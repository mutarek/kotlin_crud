package com.example.kotlin_crud.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.kotlin_crud.R
import com.example.kotlin_crud.fragments.MyHomeFragment
import com.example.kotlin_crud.fragments.NotificationFragment
import com.example.kotlin_crud.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var bottomNavigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottomNavigation = findViewById(R.id.bottomnavigation)
        setupBottomNavigationView()
        loadFragment(MyHomeFragment())


    }

    private fun setupBottomNavigationView() {
        setupClickListener()
        addBadge()
    }

    private fun addBadge() {
        bottomNavigation.getOrCreateBadge(R.id.notification).apply {
            number = 10
        }
    }

    private fun removeBadge(badgeId: Int) {
        bottomNavigation.getBadge(badgeId)?.let { badgeDrawable ->
            if (badgeDrawable.isVisible) {
                bottomNavigation.removeBadge(badgeId)
            }
        }
    }

    private fun setupClickListener() {
        bottomNavigation.setOnItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.notification -> {
                    removeBadge(it.itemId)
                    NotificationFragment()
                }
                R.id.profile -> {
                    ProfileFragment()
                }
                else -> {
                    MyHomeFragment()
                }
            }
            loadFragment(fragment)
            true

        }
    }


    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}