package com.example.egiluyapps.Home.pertemuan_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.egiluyapps.databinding.ItemProductBinding

class ProductAdapter(
    private val productList: List<ProductModel>,
    private val onItemClick: (ProductModel) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // Menggunakan val binding di constructor inner class sudah benar untuk ViewBinding
    inner class ProductViewHolder(val binding: ItemProductBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productList[position]

        // Menggunakan with(holder.binding) adalah cara yang bersih (clean)
        with(holder.binding) {
            tvProductName.text = item.name

            // Tambahkan .toString() atau template string jika price bukan String
            tvProductPrice.text = "Rp ${item.price}"

            // Setup Gambar dengan Glide
            Glide.with(root.context) // Lebih ringkas pakai root.context
                .load(item.imageUrl)
                .placeholder(android.R.drawable.ic_menu_gallery) // Tambahan: placeholder saat loading
                .into(imgProduct)

            // Listener Klik
            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = productList.size
}