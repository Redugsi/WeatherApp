package com.redugsi.weatherapp.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DataBindHelper {
    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, Integer url) {
        Glide.with(view.getContext()).load(url).into(view);
    }
}
