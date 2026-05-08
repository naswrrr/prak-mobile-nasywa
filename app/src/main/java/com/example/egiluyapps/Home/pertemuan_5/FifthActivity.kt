package com.example.egiluyapps.Home.pertemuan_5

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
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
            // MATIKAN judul default agar "egiluyapps" tidak muncul
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.arrow_back)
        }

        // --- IMPROVISASI: WARNA ICON & FONT KUSTOM ---
        // Memberikan warna biru pada icon navigasi sesuai tema
        binding.toolbar.navigationIcon?.setTint(
            ResourcesCompat.getColor(resources, R.color.blue_dark, null)
        )
        // Set Font kustom pada judul toolbar secara programmatik
        val typeface = ResourcesCompat.getFont(this, R.font.opensans_italic_variablefont_wdth)
        binding.tvToolbarTitle.typeface = typeface

        // --- IMPROVISASI: LOGIKA AUTO-HIDE TOOLBAR SAAT SCROLL ---
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Pastikan ID nestedScroll sudah ada di XML kamu
            binding.nestedScroll.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                if (scrollY > oldScrollY) {
                    binding.appBar.setExpanded(false, true) // Sembunyikan saat scroll ke bawah
                } else if (scrollY < oldScrollY) {
                    binding.appBar.setExpanded(true, true) // Tampilkan saat scroll ke atas
                }
            }
        }

        // Logic Button WebView
        binding.btnWebView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }

        // Handle Padding System Bars
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            // --- IMPROVISASI: LOGIKA DARK MODE ---
            R.id.sub_theme_light -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Toast.makeText(this, "Mode Light Aktif", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.sub_theme_dark -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Toast.makeText(this, "Mode Dark Aktif", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_search -> {
                Toast.makeText(this, "Mencari Berita...", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_settings -> {
                Toast.makeText(this, "Membuka Settings...", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}