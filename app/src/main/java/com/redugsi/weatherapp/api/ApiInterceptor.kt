package com.redugsi.weatherapp.api

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url().newBuilder().addQueryParameter("APPID", "2115a4940bd995832856dc56e1673bbd").
                addQueryParameter("mode", "json").
                addQueryParameter("units", "metric").build()
        return chain.proceed(chain.request().newBuilder().addHeader("Accept", "application/json").url(url).build())
    }

}