package com.example.networklibraryexample.network_library_kotlin.retrofit_kotlin_example

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitKtClient() {
    companion object {
        val ourInstance = RetrofitKtClient()
        const val url =
                "https://maps.googleapis.com/maps/api/place/nearbysearch/"
//        fun getInstance():RetrofitKtClient{
//            return ourInstance
//        }
    }

    val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create()) // 파싱등록
            .build()
    val service: RetrofitKtServerApi = retrofit.create(RetrofitKtServerApi::class.java)
}