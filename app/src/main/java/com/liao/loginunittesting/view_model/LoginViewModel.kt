package com.liao.loginunittesting.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel:ViewModel() {
    private val loginInformationLiveData:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun loginButtonClicked(name: String, password: String) {
        if (name == "admin" && password=="admin"){
            loginInformationLiveData.value = "Success"
        }else{
            loginInformationLiveData.value = "Wrong name or password"
        }
    }

    fun getLiveData(): MutableLiveData<String>{
        return loginInformationLiveData
    }


}