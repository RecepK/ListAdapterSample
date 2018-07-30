package com.rkurban.myapplication.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiHelper {

    companion object {
        fun getService(): Api {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Cons.BASE_LINK)
                    .client(OkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(Api::class.java)
        }
    }
}