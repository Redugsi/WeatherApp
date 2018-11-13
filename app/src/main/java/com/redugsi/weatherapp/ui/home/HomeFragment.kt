package com.redugsi.weatherapp.ui.home

import com.redugsi.weatherapp.R
import com.redugsi.weatherapp.databinding.FragmentHomeBinding
import com.redugsi.weatherapp.ui.common.BaseInjectableFragment

class HomeFragment: BaseInjectableFragment<HomeViewModel, FragmentHomeBinding>(){

    override fun getLayoutID(): Int {
        return R.layout.fragment_home
    }

    override fun initViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    companion object {
        fun newInstance(): HomeFragment{
            return HomeFragment()
        }
    }
}