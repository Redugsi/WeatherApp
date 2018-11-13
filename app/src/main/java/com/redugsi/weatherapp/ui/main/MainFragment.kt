package com.redugsi.weatherapp.ui.main

import com.redugsi.weatherapp.R
import com.redugsi.weatherapp.databinding.FragmentMainBinding
import com.redugsi.weatherapp.ui.common.BaseInjectableFragment

class MainFragment: BaseInjectableFragment<MainViewModel, FragmentMainBinding>(){

    override fun getLayoutID(): Int {
        return R.layout.fragment_main
    }

    override fun initViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    companion object {
        fun newInstance(): MainFragment{
            return MainFragment()
        }
    }

}