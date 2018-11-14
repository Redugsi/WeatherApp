package com.redugsi.weatherapp.ui.common

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Switch
import android.support.v4.view.MotionEventCompat.getActionMasked



class SwipeDisabledSwitch: Switch{

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }
}