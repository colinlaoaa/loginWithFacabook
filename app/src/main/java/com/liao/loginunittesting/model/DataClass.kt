package com.liao.loginunittesting.model

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName

data class DataClass(var image: Int, @SerializedName("title")var name: String, var id: String) {

}
