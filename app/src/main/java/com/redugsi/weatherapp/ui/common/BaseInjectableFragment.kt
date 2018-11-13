package com.redugsi.weatherapp.ui.common

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.redugsi.weatherapp.di.Injectable
import javax.inject.Inject

abstract class BaseInjectableFragment<VModel : ViewModel, DataBinding: ViewDataBinding> : Fragment(), Injectable {

    @Inject
    lateinit var factory: WeatherAppViewModelFactory

    lateinit var viewModel: VModel
    lateinit var binding: DataBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = createViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutID(), container, false)
        return binding.root
    }

    abstract fun getLayoutID(): Int

    private fun createViewModel(): VModel {
        viewModel = ViewModelProviders.of(this, factory).get(initViewModelClass())
        return viewModel
    }

    abstract fun initViewModelClass(): Class<VModel>
}