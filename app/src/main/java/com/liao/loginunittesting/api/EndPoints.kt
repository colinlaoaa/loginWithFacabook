package com.liao.loginunittesting.api

import com.liao.loginunittesting.model.DataClass
import io.reactivex.Observable
import retrofit2.http.GET

interface EndPoints {
    @GET("posts")
    fun getCall(): Observable<ArrayList<DataClass>>
}