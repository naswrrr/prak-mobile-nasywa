package com.example.egiluyapps.pertemuan_5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.egiluyapps.R
import com.example.egiluyapps.databinding.ActivityFifthBinding

class FifthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFifthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFifthBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Fifth"
            subtitle = "Ini adalah subtitle"

            // Menampilkan tombol back
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)

            // Menerapkan ikon custom dari drawable milikmu
            setHomeAsUpIndicator(R.drawable.arrow_back)
        }

        // Logic Button WebView
        binding.btnWebView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }

        // Handle Padding System Bars (Status Bar & Navigation Bar)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Menghubungkan menu XML (search & settings) ke toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // Logika ketika menu atau tombol back diklik
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // ID 'home' adalah ID bawaan Android untuk tombol back di Toolbar
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            R.id.action_search -> {
                Toast.makeText(this, "Menu Search diklik", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_settings -> {
                Toast.makeText(this, "Menu Settings diklik", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Backup handling untuk tombol navigasi ke atas
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}