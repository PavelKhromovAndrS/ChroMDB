package com.example.chromdb


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.favorite_menu -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.action_mainFragment_to_movieHistoryFragment)
                    true
                }
                R.id.search_movie_menu -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.action_mainFragment_to_contentProviderFragment)
                    true
                }
                R.id.profile_menu -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.action_mainFragment_to_mapsFragment)
                    true
                }
                else -> true
            }
        }
    }

}