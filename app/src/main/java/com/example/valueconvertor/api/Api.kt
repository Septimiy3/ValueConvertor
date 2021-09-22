package com.example.valueconvertor.api

import android.database.Observable
import com.example.valueconvertor.data.ValuteInfo
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {


    @GET("/daily_json.js")
    fun getValute():io.reactivex.Observable<ValuteInfo>

    object Instance {
        private val retrofit: Retrofit
            private get() {
                val okHttpClientBuilder = OkHttpClient.Builder()
                val retrofitBuilder = Retrofit.Builder()
                retrofitBuilder.baseUrl(DOMAIN)
                retrofitBuilder.addConverterFactory(GsonConverterFactory.create())
                retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                retrofitBuilder.client(okHttpClientBuilder.build())
                return retrofitBuilder.build()
            }
        fun getApi(): Api? {
            return retrofit.create(Api::class.java)
        }
    }

    companion object {
        const val DOMAIN = "https://www.cbr-xml-daily.ru/"
    }
}