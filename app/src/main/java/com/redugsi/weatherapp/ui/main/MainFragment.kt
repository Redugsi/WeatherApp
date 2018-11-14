package com.redugsi.weatherapp.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.redugsi.weatherapp.MainActivity
import com.redugsi.weatherapp.R
import com.redugsi.weatherapp.databinding.FragmentMainBinding
import com.redugsi.weatherapp.ui.common.BaseInjectableFragment
import com.redugsi.weatherapp.ui.common.navigation.MainActivityNavigationController
import javax.inject.Inject


class MainFragment : BaseInjectableFragment<MainViewModel, FragmentMainBinding>(), BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var navigator: MainActivityNavigationController

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.menu_home -> binding.viewpager.currentItem = 0
            R.id.menu_camera ->{
                navigator.toCamera()
                return false
            }
            R.id.menu_settings -> {
                navigator.toSettings()
                return false
            }
        }
        return true
    }

    private lateinit var pagerAdapter: MainPagerAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initial()
    }

    private fun initial() {
        pagerAdapter = MainPagerAdapter(childFragmentManager)
        binding.viewpager.adapter = pagerAdapter
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_main
    }

    override fun initViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }
}