package com.example.chromdb


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        bottomNavigationView.setOnItemSelectedListener {
            if (it.itemId == R.id.favorite_menu) {
                Toast.makeText(applicationContext, "goToFavorite", Toast.LENGTH_SHORT).show()
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_mainFragment_to_favoriteMovieFragment)
                true
            } else {
                true
            }
        }
    }

}