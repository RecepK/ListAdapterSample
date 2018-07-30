package com.rkurban.myapplication.api

import com.rkurban.myapplication.app.TestModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET(Cons.LINK)
    fun apiList(@Query("indent") indent: String): Call<List<TestModel>>


    @GET(Cons.LINK)
    fun apiListRx(@Query("indent") indent: String): Observable<List<TestModel>>


}