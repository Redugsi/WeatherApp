package com.redugsi.weatherapp

import android.Manifest
import android.os.Bundle
import com.redugsi.weatherapp.api.WeatherApi
import com.redugsi.weatherapp.ui.common.BaseInjectorActivity
import com.redugsi.weatherapp.ui.common.navigation.MainActivityNavigationController
import com.tbruyelle.rxpermissions2.RxPermissions

import javax.inject.Inject
import android.content.Intent
import android.graphics.Bitmap
import android.app.Activity
import com.redugsi.weatherapp.event.PictureTakenEvent
import com.redugsi.weatherapp.event.RxBus


class MainActivity : BaseInjectorActivity() {

    private val CAMERA_REQUEST = 1888

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
