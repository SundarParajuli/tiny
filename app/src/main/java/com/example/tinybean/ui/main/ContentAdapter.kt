package com.example.tinybean.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tinybean.data.model.dto.ContentDto
import com.example.tinybean.databinding.ItemDoubleColImageBinding
import com.example.tinybean.databinding.ItemFourColImageBinding
import com.example.tinybean.databinding.ItemSingleColImageBinding
import com.example.tinybean.databinding.ItemSingleColImageSeparatorBinding
import com.example.tinybean.ui.main.ContentAdapter.viewType

class ContentAdapter : ListAdapter<ContentDto, RecyclerView.ViewHolder>(DiffCallback()) {
    private lateinit var itemSingleColImageBinding: ItemSingleColImageBinding
    private lateinit var itemSingleColImageSeparatorBinding: ItemSingleColImageSeparatorBinding
    private lateinit var itemDoubleColImageBinding: ItemDoubleColImageBinding
    private lateinit var itemFourColImageBinding: ItemFourColImageBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {
        return when (viewType) {
            ContentAdapter.viewType.IMAGE_SINGLE_COL -> {
                itemSingleColImageBinding = ItemSingleColImageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                SingleColumnImageViewHolder(itemSingleColImageBinding)
            }
            ContentAdapter.viewType.IMAGE_DOUBLE_COL -> {
                itemDoubleColImageBinding = ItemDoubleColImageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                DoubleColumnImageViewHolder(itemDoubleColImageBinding)
            }
            ContentAdapter.viewType.IMAGE_TRIPPLE_COL -> {
                itemDoubleColImageBinding = ItemDoubleColImageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                DoubleColumnImageViewHolder(itemDoubleColImageBinding)
            }
            ContentAdapter.viewType.IMAGE_FOUR_COL -> {
                itemFourColImageBinding = ItemFourColImageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                FourColumnImageViewHolder(itemFourColImageBinding)
            }
            else -> {
                itemSingleColImageBinding = ItemSingleColImageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                SingleColumnImageViewHolder(itemSingleColImageBinding)
            }
        }
    }

    fun updateList(list: List<ContentDto>) = submitList(list)

    override fun getItemViewType(position: Int): Int {
        val contentDto = getItem(position)
        return when (contentDto.type) {
            "image" -> {
                return when (contentDto.cols) {
                    1 -> viewType.IMAGE_SINGLE_COL
                    2 -> viewType.IMAGE_DOUBLE_COL
                    3 -> viewType.IMAGE_TRIPPLE_COL
                    4 -> viewType.IMAGE_FOUR_COL
                    else -> {
                        viewType.IMAGE_SINGLE_COL
                    }
                }
            }
            "carousel" -> {
                super.getItemViewType(position)
            }
            "slider" -> {
                super.getItemViewType(position)
            }
            else -> {
                super.getItemViewType(position)
            }
        }
    }

    inner class SingleColumnImageViewHolder(private val binding: ItemSingleColImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contentDto: ContentDto) {

        }
    }

    inner class DoubleColumnImageViewHolder(private val binding: ItemDoubleColImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contentDto: ContentDto) {

        }
    }

    inner class TrippleColumnImageViewHolder(private val binding: ItemSingleColImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contentDto: ContentDto) {

        }
    }

    inner class FourColumnImageViewHolder(private val binding: ItemFourColImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contentDto: ContentDto) {

        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ContentDto>() {
        override fun areItemsTheSame(oldItem: ContentDto, newItem: ContentDto) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: ContentDto, newItem: ContentDto) =
            oldItem == newItem
    }

    object viewType {
        const val IMAGE_SINGLE_COL = 1
        const val IMAGE_SINGLE_COL_SEPARATOR = 2
        const val IMAGE_DOUBLE_COL = 3
        const val IMAGE_TRIPPLE_COL = 3
        const val IMAGE_FOUR_COL = 4
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

        }
    }
}