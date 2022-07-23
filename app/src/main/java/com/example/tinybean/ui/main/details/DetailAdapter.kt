package com.example.tinybean.ui.main.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tinybean.data.model.dto.Image
import com.example.tinybean.databinding.ItemListImageBinding

class DetailAdapter : ListAdapter<Image, DetailAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = getItem(position)
        holder.bind(image)
    }

    fun updateList(imageList: List<Image>) {
        submitList(imageList)
    }

    inner class ViewHolder(private val binding: ItemListImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) {
            Glide.with(binding.root).load(image.url)
                .into(binding.ivFirst)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image) =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: Image, newItem: Image) =
            oldItem == newItem
    }
}