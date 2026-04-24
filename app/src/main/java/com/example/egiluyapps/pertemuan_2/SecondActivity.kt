package com.example.egiluyapps.pertemuan_2

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.egiluyapps.R // Tambahkan import R ini agar drawable terbaca
import com.example.egiluyapps.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Second"
            subtitle = "Pinterest Style"

            // Tampilkan tombol back
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)

            // Pasang ikon panah custom milikmu
            setHomeAsUpIndicator(R.drawable.arrow_back)
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSubmit.setOnClickListener {
            val nama = binding.inputNama.text
            Log.e("Klik btnSubmit", "Tombol berhasil di tekan. Isi dari inputNama = $nama")

            Toast.makeText(this, "Anda telah melakukan klik pada tombol Submit $nama", Toast.LENGTH_SHORT).show()
        }
    }

    // Menangani klik tombol back di toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Tambahan agar fungsi klik navigasi atas (tombol panah) berjalan mulus
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}