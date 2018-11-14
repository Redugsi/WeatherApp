package com.redugsi.weatherapp.util

import com.bumptech.glide.Glide
import android.databinding.BindingAdapter
import android.widget.ImageView


class DataBinder {

    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: Int) {
        val context = imageView.context
        Glide.with(context).load(url).into(imageView)
    }
}