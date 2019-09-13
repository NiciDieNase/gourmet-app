package de.nicidienase.geniesser_app

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class GourmetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gourmet)
        val navController = findNavController(R.id.nav_host)
        val bottonNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottonNav.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController(R.id.nav_host).popBackStack()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return false
    }
}