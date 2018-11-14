package com.redugsi.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.redugsi.weatherapp.api.WeatherApi
import com.redugsi.weatherapp.ui.common.BaseInjectorActivity
import com.redugsi.weatherapp.ui.common.navigation.MainActivityNavigationController
import com.tbruyelle.rxpermissions2.RxPermissions

import javax.inject.Inject
import android.location.Geocoder
import android.widget.ImageView
import com.redugsi.weatherapp.util.Util
import com.redugsi.weatherapp.util.connect
import com.redugsi.weatherapp.util.toast
import java.util.*
import android.content.Intent
import android.R.attr.data
import android.support.v4.app.NotificationCompat.getExtras
import android.graphics.Bitmap
import android.app.Activity
import android.util.Log
import com.redugsi.weatherapp.event.PictureTakenEvent
import com.redugsi.weatherapp.event.RxBus


class MainActivity : BaseInjectorActivity() {

    private val CAMERA_REQUEST = 1888
    private val imageView: ImageView? = null
    private val MY_CAMERA_PERMISSION_CODE = 100

    private lateinit var rxPermissions: RxPermissions

    @Inject
    lateinit var navigator: MainActivityNavigationController

    @Inject
    lateinit var api: WeatherApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator.toMain()
    }

    fun openCamera(){
        checkCameraPermission()
    }

    private fun checkCameraPermission(){
        rxPermissions = RxPermissions(this)
        rxPermissions
                .request(Manifest.permission.CAMERA)
                .subscribe { granted ->
                    if (granted) {
                        onCameraPermissionGranted()
                    }
                }
    }

    private fun onCameraPermissionGranted(){
        val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode === CAMERA_REQUEST && resultCode === Activity.RESULT_OK && data != null) {
            val photoBm = data.extras.get("data") as Bitmap
            if (photoBm != null){
                RxBus.getInstance().send(PictureTakenEvent(photoBm))
            }
        }
    }
}
