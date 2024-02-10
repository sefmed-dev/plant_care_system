package com.example.dog_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // importer la bottomnavigationview
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        // Load the "Vote" fragment by default
        loadFragment(Vote(this))
        navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.vote_page -> {
                    loadFragment(Vote(this ))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.breeds_page -> {
                    loadFragment(Breeds(this))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.images_page -> {
                    loadFragment(ImagesR(this))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.favorites_page -> {
                    loadFragment(Favourites(this))
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    loadFragment(Vote(this ))
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }
    }

    private fun loadFragment (fragment: Fragment) {
    // injecter le fragment dans notre boite (fragment_container)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}