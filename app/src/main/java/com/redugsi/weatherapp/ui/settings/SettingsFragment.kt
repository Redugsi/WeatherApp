package com.redugsi.weatherapp.ui.settings

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.redugsi.weatherapp.R
import com.redugsi.weatherapp.databinding.FragmentSettingsBinding
import com.redugsi.weatherapp.ui.common.BaseInjectableFragment
import com.redugsi.weatherapp.util.StoreManager
import javax.inject.Inject
import android.os.BatteryManager
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import com.redugsi.weatherapp.util.isWifiEnabled
import com.redugsi.weatherapp.util.openWifiSettingPage


class SettingsFragment : BaseInjectableFragment<SettingsViewModel, FragmentSettingsBinding>() {

    lateinit var citiesAdapter: CitiesAdapter

    @Inject
    lateinit var storeManager: StoreManager

    override fun getLayoutID(): Int {
        return R.layout.fragment_settings
    }

    override fun initViewModelClass(): Class<SettingsViewModel> {
        return SettingsViewModel::class.java
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initial()
        observe()
    }

    private fun initial() {
        binding.viewModel = viewModel
        binding.checkBox.isChecked = storeManager.isGpsSearchActive()
        initClickEvents()
        initRecycler()
        initBatteryStatus()
        initWifiStatus()
    }

    private fun observe() {
        viewModel.onSaved.observe(this@SettingsFragment, Observer {
            backPress()
        })
    }

    private fun initRecycler() {
        citiesAdapter = CitiesAdapter(storeManager)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = citiesAdapter
    }

    private fun initBatteryStatus() {
        activity?.registerReceiver(this.mBatInfoReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    private fun initClickEvents(){
        binding.back.setOnClickListener({
            backPress()
        })
        binding.save.setOnClickListener({
            viewModel.saveSettings(storeManager, citiesAdapter.choosedCityName, binding.checkBox.isChecked)
        })
        binding.switchContainer.setOnClickListener({
            activity?.openWifiSettingPage()
        })
    }

    private fun initWifiStatus(){
        if (isAdded)
        binding.wifiEnabled = activity?.isWifiEnabled()
    }

    private fun backPress() {
        if (activity != null) {
            activity!!.onBackPressed()
        }
    }

    private val mBatInfoReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
            if (isAdded) {
                binding.batteryLevel = level
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.unregisterReceiver(mBatInfoReceiver)
    }
}