package com.redugsi.weatherapp.ui.common

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import com.redugsi.weatherapp.util.Constants

open class BaseFragment(): Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    companion object {

        fun <T> newInstance(fragment: T  , o : Any? = null ) : T {
            var bundle = Bundle()

            if (o != null) {
                when (o) {
                    is String -> bundle.putString(Constants.FRAGMENT_DATA, o)
                    is Boolean -> bundle.putBoolean(Constants.FRAGMENT_DATA, o)
                    is Int -> bundle.putInt(Constants.FRAGMENT_DATA, o)
                    is Float -> bundle.putFloat(Constants.FRAGMENT_DATA, o)
                    is Byte -> bundle.putByte(Constants.FRAGMENT_DATA, o)
                    is ByteArray -> bundle.putByteArray(Constants.FRAGMENT_DATA, o)
                    is Char -> bundle.putChar(Constants.FRAGMENT_DATA, o)
                    is Short -> bundle.putShort(Constants.FRAGMENT_DATA, o)
                    is IntArray -> bundle.putIntArray(Constants.FRAGMENT_DATA, o)
                    is Parcelable -> bundle.putParcelable(Constants.FRAGMENT_DATA, o)
                }
            }

            (fragment as Fragment).arguments = bundle
            return fragment
        }


    }


    fun showAlertDialog(context: Context, title: String,
                        positiveButton: String, positiveListener: DialogInterface.OnClickListener,
                        message: String, cancelable: Boolean) {

        val alertDialog = AlertDialog.Builder(context)
                .setPositiveButton(positiveButton, positiveListener).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setCanceledOnTouchOutside(cancelable)
        alertDialog.setCancelable(cancelable)
        alertDialog.show()
    }
}