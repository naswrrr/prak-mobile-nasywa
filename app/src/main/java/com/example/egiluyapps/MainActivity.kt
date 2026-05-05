package com.example.egiluyapps

import android.content.Context // Tambahkan ini
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.egiluyapps.databinding.ActivityMainBinding
import com.example.egiluyapps.pertemuan_4.FourthActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi SharedPreferences agar bisa dipakai di dalam tombol logout
        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Fitur Pertemuan 4 (Sudah ada di kode kamu)
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra("nama", "Politeknik Caltex Riau")
            intent.putExtra("asal", "Rumbai")
            intent.putExtra("usia", 25)
            startActivity(intent)
        }

        // --- LOGIKA LOGOUT FIX (SESUAI PERINTAH) ---
        binding.btnLogout.setOnClickListener {
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    // 1. Ambil sharedPref dan hapus SEMUA data (isLogin & username)
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    // 2. Tutup dialog biar gak gantung
                    dialog.dismiss()

                    // 3. Pindah kembali ke AuthActivity (Login)
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)

                    // 4. Selesaikan MainActivity supaya gak bisa di-"Back"
                    finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}