package com.redugsi.weatherapp.di

import com.redugsi.weatherapp.WeatherApp
import com.redugsi.weatherapp.api.ApiInterceptor
import com.redugsi.weatherapp.api.WeatherApi
import com.redugsi.weatherapp.util.LiveDataCallAdapterFactory
import com.redugsi.weatherapp.util.StoreManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(): OkHttpClient{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(ApiInterceptor())
                .build()
        return client
    }

    @Singleton
    @Provides
    fun provideGithubService(client: OkHttpClient): WeatherApi {
        return Retrofit.Builder()
                .baseUrl(WeatherApi.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideStoreManager() : StoreManager {
        return StoreManager(WeatherApp.sContext)
    }

}