package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemProductHolderBinding
import com.example.myapplication.model.Product

class ProductsAdapter() :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {
    var items = ArrayList<Product>()
    lateinit var binding: ItemProductHolderBinding

    fun setDataList(data: ArrayList<Product>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        binding =
            ItemProductHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class ProductViewHolder(private val binding: ItemProductHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Product) {
            binding.productData = data
            binding.executePendingBindings()
            loadImage(binding.imIcon, data.image)
        }

        private fun loadImage(icon: ImageView, url: String) {
            Glide.with(icon)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(icon)
        }

    }

}