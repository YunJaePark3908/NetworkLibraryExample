package com.example.networklibraryexample.network_library_kotlin.retrofit_kotlin_example

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitKtServerApi {
    // 주소 및 Type을 정하는 인터페이스
    @GET("json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyBrJ3ec9wTuS6L-xHkaXLU8BJbFsx_LZ9o")
    fun getMyJsonObject(): Call<JsonObject?>?
}