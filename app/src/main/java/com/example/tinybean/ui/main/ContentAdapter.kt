package com.example.tinybean.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ActionTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.TouchListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.tinybean.data.model.dto.ContentDto
import com.example.tinybean.databinding.ItemCarouselViewBinding
import com.example.tinybean.databinding.ItemColImageBinding
import com.example.tinybean.databinding.ItemSliderBinding
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class ContentAdapter(private val onClickListener: () -> Unit) :
    ListAdapter<ContentDto, RecyclerView.ViewHolder>(DiffCallback()) {
    private lateinit var itemColImageBinding: ItemColImageBinding
    private lateinit var itemSliderBinding: ItemSliderBinding
    private lateinit var itemCarouselViewBinding: ItemCarouselViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ViewType.IMAGE_TYPE -> {
                itemColImageBinding =
                    ItemColImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ImageViewHolder(itemColImageBinding)
            }
            ViewType.SLIDER_TYPE -> {
                itemSliderBinding =
                    ItemSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return SliderViewHolder(itemSliderBinding)
            }
            ViewType.CAROUSEL_TYPE -> {
                itemCarouselViewBinding =
                    ItemCarouselViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return CarouseViewHolder(itemCarouselViewBinding)
            }
            else -> {
                itemCarouselViewBinding =
                    ItemCarouselViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return CarouseViewHolder(itemCarouselViewBinding)

            }
        }

    }

    fun updateList(list: List<ContentDto>) = submitList(list)

    override fun getItemViewType(position: Int): Int {
        val contentDto = getItem(position)
        return when (contentDto.type) {
            "image" -> {
                ViewType.IMAGE_TYPE
            }
            "carousel" -> {
                ViewType.CAROUSEL_TYPE
            }
            "slider" -> {
                ViewType.SLIDER_TYPE
            }
            else -> {
                ViewType.IMAGE_TYPE
            }
        }
    }

    inner class CarouseViewHolder(private val binding: ItemCarouselViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contentDto: ContentDto) {
            val list = contentDto.images.map { CarouselItem(it.url, contentDto.title) }
            binding.carousel.setData(list)
            binding.root.setOnClickListener {
                onClickListener.invoke()
            }
        }
    }

    inner class SliderViewHolder(private val binding: ItemSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contentDto: ContentDto) {
            val imageList =
                contentDto.images.map { SlideModel(it.url, scaleType = ScaleTypes.FIT) }
            binding.imageSlider.setImageList(imageList)

            binding.imageSlider.setTouchListener(object : TouchListener {
                override fun onTouched(touched: ActionTypes) {
                    when (touched) {
                        ActionTypes.DOWN, ActionTypes.MOVE -> {
                            binding.imageSlider.stopSliding()
                        }
                        ActionTypes.UP -> {
                            binding.imageSlider.startSliding()

                        }
                    }
                }

            })

        }
    }

    inner class ImageViewHolder(private val binding: ItemColImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contentDto: ContentDto) {
            binding.imageContainer.removeAllViews()
            contentDto.images.forEach {
                val image = ImageView(binding.root.context)
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.weight = 1f
                image.layoutParams = params
                Glide.with(binding.root).load(it.url)
                    .into(image)
                binding.imageContainer.addView(image)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ContentDto>() {
        override fun areItemsTheSame(oldItem: ContentDto, newItem: ContentDto) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: ContentDto, newItem: ContentDto) =
            oldItem == newItem
    }

    object ViewType {
        const val IMAGE_TYPE = 1
        const val SLIDER_TYPE = 2
        const val CAROUSEL_TYPE = 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contentDto = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.invoke()
        }
        when (holder) {
            is ImageViewHolder -> {
                holder.bind(contentDto)
            }
            is SliderViewHolder -> {
                holder.bind(contentDto)
            }
            is CarouseViewHolder -> {
                holder.bind(contentDto)
            }
        }
    }
}