package com.example.egiluyapps.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.egiluyapps.R
import com.example.egiluyapps.databinding.ActivityThirdBinding
import com.example.egiluyapps.pertemuan_3.ThirdResultActivity // Pastikan import ini benar sesuai package-mu

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Third"
            subtitle = "Kirim Pesan"

            // Tampilkan tombol back
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)

            // Pakai icon panah custom milikmu
            setHomeAsUpIndicator(R.drawable.arrow_back)
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnKirim.setOnClickListener {
            val nomor = binding.inputNoTujuan.text
            Log.e("Klik btnKirim","Tombol berhasil di tekan. Isi dari inputNama = $nomor")

            Toast.makeText(this, "Pesan berhasil terkirim ke: $nomor" , Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ThirdResultActivity::class.java)
            startActivity(intent)
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

    // Tambahan agar fungsi klik navigasi atas sinkron
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}