package com.redugsi.weatherapp.ui.settings

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.redugsi.weatherapp.event.OnSettingsSavedEvent
import com.redugsi.weatherapp.event.RxBus
import com.redugsi.weatherapp.util.StoreManager
import javax.inject.Inject

class SettingsViewModel @Inject constructor() : ViewModel(){

    var onSaved: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun saveSettings(storeManager: StoreManager, cityName: String?, checked: Boolean){
        if (cityName != null){
            saveCity(storeManager, cityName!!)
        }

        saveUseGpsForWeatherForecast(storeManager,checked)
        RxBus.getInstance().send(OnSettingsSavedEvent())
        onSaved.value = true
    }

    fun saveCity(storeManager: StoreManager, cityName: String){
        storeManager.setChoosedCityName(cityName)
    }

    fun saveUseGpsForWeatherForecast(storeManager: StoreManager, checked: Boolean){
        storeManager.setGpsActive(checked)
    }
}