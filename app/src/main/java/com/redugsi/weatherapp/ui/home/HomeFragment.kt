package com.redugsi.weatherapp.ui.home

import android.os.Bundle
import com.redugsi.weatherapp.R
import com.redugsi.weatherapp.databinding.FragmentHomeBinding
import com.redugsi.weatherapp.event.OnSettingsSaved
import com.redugsi.weatherapp.event.PictureTakenEvent
import com.redugsi.weatherapp.event.RxBus
import com.redugsi.weatherapp.ui.camera.CameraFragment
import com.redugsi.weatherapp.ui.common.BaseInjectableFragment
import com.redugsi.weatherapp.util.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeFragment: BaseInjectableFragment<HomeViewModel, FragmentHomeBinding>(){


    private lateinit var pagerAdapter: HomePagerAdapter

    private var disposables: CompositeDisposable = CompositeDisposable()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initial()
        observePictureTakenEvent()
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_home
    }

    override fun initViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    private fun initial(){
        pagerAdapter = HomePagerAdapter(childFragmentManager)
        binding.viewpager.adapter = pagerAdapter
    }

    private fun observePictureTakenEvent(){
        disposables.add(RxBus.getInstance().toObserverable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { o ->
                    if (o is PictureTakenEvent) {
                        val bitmap = o.bitmap
                        if (bitmap != null){
                            val fragment = CameraFragment.newInstance(bitmap)
                            pagerAdapter.addNewFragment(fragment)
                        }else{
                            activity?.toast(resources.getString(R.string.photo_error))
                        }
                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (disposables != null && !disposables.isDisposed){
            disposables.dispose()
        }
    }

    companion object {
        fun newInstance(): HomeFragment{
            return HomeFragment()
        }
    }
}