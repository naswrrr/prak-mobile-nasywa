package com.example.egiluyapps.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.egiluyapps.AuthActivity
import com.example.egiluyapps.Home.pertemuan_4.FourthActivity
import com.example.egiluyapps.Home.pertemuan_7.SeventhActivity
import com.example.egiluyapps.R
import com.example.egiluyapps.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }


        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java)
            intent.putExtra("nama", "Politeknik Caltex Riau")
            intent.putExtra("asal", "Rumbai")
            intent.putExtra("usia", 25)
            startActivity(intent)
        }

        binding.btnToSeventh.setOnClickListener {
            val intent = Intent(requireContext(), SeventhActivity::class.java)
            startActivity(intent)
        }

        // --- LOGIKA LOGOUT FIX (SESUAI PERINTAH) ---
        binding.btnLogout.setOnClickListener {
            androidx.appcompat.app.AlertDialog.Builder(requireContext())
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
                    val intent = Intent(requireContext(), AuthActivity::class.java)
                    startActivity(intent)

                    // 4. Selesaikan MainActivity supaya gak bisa di-"Back"
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}
