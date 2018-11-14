package com.redugsi.weatherapp.event

import android.graphics.Bitmap

class PictureTakenEvent(bitmap: Bitmap?) {
    var bitmap: Bitmap? = null

    init {
        this.bitmap = bitmap
    }
}