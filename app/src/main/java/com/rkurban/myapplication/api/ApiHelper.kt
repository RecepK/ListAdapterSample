package com.rkurban.myapplication.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHelper {

    companion object {

        fun getService(): Api {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Cons.BASE_LINK)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(Api::class.java)
        }
    }
}