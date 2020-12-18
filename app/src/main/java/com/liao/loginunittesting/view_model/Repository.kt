package com.liao.loginunittesting.view_model

import com.liao.loginunittesting.api.Client
import com.liao.loginunittesting.model.DataClass
import io.reactivex.Observable

open class Repository {
    open fun getApiCall():Observable<ArrayList<DataClass>> {
        return Client.getClientInstance().getCall()
    }
}