package com.redugsi.weatherapp.ui.weather

import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.location.Location
import android.os.Bundle
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.redugsi.weatherapp.R
import com.redugsi.weatherapp.api.WeatherApi
import com.redugsi.weatherapp.databinding.FragmentWeatherBinding
import com.redugsi.weatherapp.event.OnSettingsSaved
import com.redugsi.weatherapp.event.RxBus
import com.redugsi.weatherapp.ui.common.BaseInjectableFragment
import com.redugsi.weatherapp.util.ResourceUtil
import com.redugsi.weatherapp.util.StoreManager
import com.redugsi.weatherapp.util.Util
import com.redugsi.weatherapp.util.toast
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class WeatherFragment : BaseInjectableFragment<WeatherViewModel, FragmentWeatherBinding>() {

    @Inject
    lateinit var api: WeatherApi

    @Inject
    lateinit var storeManager: StoreManager

    private lateinit var rxPermissions: RxPermissions
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    var disposables: CompositeDisposable = CompositeDisposable()


    override fun getLayoutID(): Int {
        return R.layout.fragment_weather
    }

    override fun initViewModelClass(): Class<WeatherViewModel> {
        return WeatherViewModel::class.java
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initial()
    }

    private fun initial() {
        binding.viewModel = viewModel
        observeViewModel()
        checkGpsPermissionThenWeatherData()
    }

    private fun observeViewModel() {
        viewModel.weatherLiveData.observe(this@WeatherFragment, Observer {
                if (it != null) {
                    binding.entity = it
                    binding.imageUrl = ResourceUtil.getResourceIdBuyWeatherConditionCode(it.cod!!)
                }
        })

        viewModel.onRequestFailed.observe(this@WeatherFragment, Observer {
            if (it != null && activity != null && it!!){
                activity!!.toast(getString(R.string.city_not_found))
            }
        })

        disposables.add(RxBus.getInstance().toObserverable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { o ->
                    if (o is OnSettingsSaved) {
                        checkGpsPermissionThenWeatherData()
                    }
                })
    }

    @SuppressLint("MissingPermission")
    private fun checkGpsPermissionThenWeatherData() {
        if (storeManager.isGpsSearchActive()) {
            rxPermissions = RxPermissions(this)
            rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    .subscribe { granted ->
                        if (granted && activity != null) {
                            fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)
                            fusedLocationClient.lastLocation
                                    .addOnSuccessListener { location: Location? ->
                                        onPermissionAccepted(location)
                                    }
                        } else {
                            onPermissinDenied()
                        }
                    }
        } else {
            onPermissinDenied()
        }
    }

    private fun onPermissionAccepted(location: Location?){
        if (location != null && activity != null) {
            val cityName = Util.getCityName(activity!!, location!!)
            if (cityName != null) {
                storeManager.setChoosedCityName(cityName)
            }
        }else{
            activity?.toast(resources.getString(R.string.gps_error))
        }
        viewModel.getWeatherForecast()
    }

    private fun onPermissinDenied(){
        viewModel.getWeatherForecast()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (disposables != null && !disposables.isDisposed){
            disposables.dispose()
        }
    }

    companion object {
        fun newInstance(): WeatherFragment {
            return WeatherFragment()
        }
    }

}