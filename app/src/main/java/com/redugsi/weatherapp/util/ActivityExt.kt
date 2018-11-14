package com.redugsi.weatherapp.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

fun Activity.toast(message : CharSequence, duration : Int = Toast.LENGTH_LONG) =
        Toast.makeText(this,message,duration).show()

inline fun Activity.alertDialog(body : AlertDialog.Builder.() -> AlertDialog.Builder) : AlertDialog {
    return AlertDialog.Builder(this)
            .body()
            .show()
}

/**
 * AppCompatActivity's toolbar visibility modifiers
 */

infix fun Activity.connect(context : Context?) : Boolean{
    val connMgr = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connMgr.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnectedOrConnecting
}

fun Activity.isWifiEnabled() : Boolean{
    val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    return wifiManager.isWifiEnabled
}

fun Activity.openWifiSettingPage(){
    startActivity(Intent(Settings.ACTION_WIFI_SETTINGS));
}

fun AppCompatActivity.hideToolbar() {
    supportActionBar?.hide()
}


fun AppCompatActivity.showToolbar() {
    supportActionBar?.show()
}