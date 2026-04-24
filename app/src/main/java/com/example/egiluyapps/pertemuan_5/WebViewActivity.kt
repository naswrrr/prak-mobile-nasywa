package com.example.egiluyapps.pertemuan_5

import android.os.Build
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.egiluyapps.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi Binding
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // 2. Mengaktifkan Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Web Merdeka"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // 3. Konfigurasi WebView
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://merdeka.com")

        // 4. Agar Toolbar hide/show saat scroll web (Sesuai instruksi dosen)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                if (scrollY > oldScrollY) {
                    binding.appBar.setExpanded(false, true) // sembunyikan
                } else if (scrollY < oldScrollY) {
                    binding.appBar.setExpanded(true, true) // tampilkan
                }
            }
        }

        // 5. Mengaktifkan tombol back pada toolbar (Panah kiri atas)
        binding.toolbar.setNavigationOnClickListener {
            handleBackAction()
        }

        // 6. Menangani tombol back HP
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackAction()
            }
        })

        // 7. Pengaturan Padding System Bars
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Fungsi bantuan agar logika Back seragam antara tombol HP & tombol Toolbar
    private fun handleBackAction() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack() // Kembali ke halaman sebelumnya
        } else {
            finish() // Keluar dari activity
        }
    }
}