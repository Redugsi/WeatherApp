package com.redugsi.weatherapp.ui.settings

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.redugsi.weatherapp.databinding.ItemCityBinding
import com.redugsi.weatherapp.util.StoreManager
import java.util.*

class CitiesAdapter(var storeManager: StoreManager) : RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {

    private val cityNames = arrayOf("Adana","Adıyaman","Afyon","Ağrı","Amasya","Ankara","Artvin","Aydın","Balıkesir","Bilecik","Bingöl","Bitlis","Bolu","Burdur","Bursa","Çanakkale","Çankırı","Çorum","Denizli","Diyarbakır","Edirne","Elazığ","Erzincan","Erzurum","Eskişehir","Gaziantep","Giresun","Gümüşhane","Hakkari","Hatay","Isparta","Mersin","Istanbul","Izmir","Kars","Kastamonu","Kayseri","Kırklareli","Kırşehir","Kocaeli","Konya","Kütahya","Malatya","Manisa","maras","Mardin","Muğla","Muş","Nevşehir","Niğde","Ordu","Rize","Sakarya","Samsun","Siirt","Sinop","Sivas","Tekirdağ","Tokat","Trabzon","Tunceli","Şanlıurfa","Uşak","Van","Yozgat","Zonguldak","Aksaray","Bayburt","Karaman","Kırıkkale","Batman","Şırnak","Bartın","Ardahan","Iğdır","Yalova","Karabük","Kilis","Osmaniye","Düzce")
    private var choosedCityModels: ArrayList<ChoosedCityModel> = ArrayList<ChoosedCityModel>()

    var choosedCityName: String? = null
    var lastChoosedIndex: Int? = null

    init {
        choosedCityName = storeManager.getChoosedCityName()

        for (cityName in cityNames) {
            choosedCityModels.add(ChoosedCityModel(cityName, false))
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CityViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        val binding = ItemCityBinding.inflate(inflater)
        return CityViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cityNames.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, p1: Int) {
        val entity = choosedCityModels[p1]

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            entity.choosed = entity.cityName.toLowerCase(Locale.forLanguageTag("tr-TR")) == choosedCityName?.toLowerCase(Locale.forLanguageTag("tr-TR"))
        }else{
            entity.choosed = entity.cityName.toLowerCase(Locale.getDefault()) == choosedCityName?.toLowerCase(Locale.getDefault())
        }

        if (entity.choosed){
            lastChoosedIndex = p1
        }

        holder.binding.entity = entity
        holder.binding.root.setOnClickListener(View.OnClickListener {
            choosedCityName = entity.cityName
            notifyItemChanged(p1)

            if (lastChoosedIndex != null){
                notifyItemChanged(lastChoosedIndex!!)
            }

            lastChoosedIndex = p1
        })
    }


    inner class CityViewHolder(val binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root) {}

}