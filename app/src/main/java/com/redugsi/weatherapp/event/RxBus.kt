package com.redugsi.weatherapp.event

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class RxBus private constructor() {
    private val _bus = PublishSubject.create<Any>()

    fun send(o: Any) {
        _bus.onNext(o)
    }

    fun toObserverable(): Observable<Any> {
        return _bus
    }

    fun hasObservers(): Boolean {
        return _bus.hasObservers()
    }

    companion object {
        var mInstance: RxBus? = null

        fun getInstance(): RxBus{
            if (mInstance == null){
                mInstance = RxBus()
            }
            return mInstance!!
        }
    }
}