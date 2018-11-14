package com.redugsi.weatherapp.ui.camera

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.redugsi.weatherapp.R
import com.redugsi.weatherapp.di.Injectable
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory
import android.widget.ImageView


class TakenPhotoPreviewFragment: Fragment(), Injectable{

    private lateinit var imageView: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_camera, container, false)
        imageView = view.findViewById(R.id.photo)
        checkBundle()
        return view
    }

    private fun checkBundle(){
        val byteArray = arguments?.getByteArray(BUNDLE_BITMAP)
        if (byteArray != null){
            val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)
            imageView.setImageBitmap(bmp)
        }
    }

    companion object {

        val BUNDLE_BITMAP = "bundle_bitmap"

        fun newInstance(bitmap: Bitmap): TakenPhotoPreviewFragment {
            val fragment = TakenPhotoPreviewFragment()
            val args = Bundle()
            args.putByteArray(BUNDLE_BITMAP, bitmapToByteArray(bitmap))
            fragment.arguments = args
            return fragment
        }

        fun bitmapToByteArray(bitmap: Bitmap):ByteArray{
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)
            val byteArray = stream.toByteArray()
            return byteArray
        }
    }
}