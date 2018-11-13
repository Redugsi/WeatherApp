package com.redugsi.weatherapp.ui.settings

import com.redugsi.weatherapp.R
import com.redugsi.weatherapp.databinding.FragmentSettingsBinding
import com.redugsi.weatherapp.ui.common.BaseInjectableFragment

class SettingsFragment: BaseInjectableFragment<SettingsViewModel, FragmentSettingsBinding>(){

    override fun getLayoutID(): Int {
        return R.layout.fragment_settings
    }

    override fun initViewModelClass(): Class<SettingsViewModel> {
        return SettingsViewModel::class.java
    }
}