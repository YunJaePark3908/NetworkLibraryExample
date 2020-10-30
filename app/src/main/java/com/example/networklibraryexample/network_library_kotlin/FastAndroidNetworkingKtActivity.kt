package com.example.networklibraryexample.network_library_kotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.networklibraryexample.R
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jacksonandroidnetworking.JacksonParserFactory
import kotlinx.android.synthetic.main.activity_networking_result.*
import okhttp3.OkHttpClient
import org.json.JSONException
import org.json.JSONObject

class FastAndroidNetworkingKtActivity : AppCompatActivity() {
    private val url =
        "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyBrJ3ec9wTuS6L-xHkaXLU8BJbFsx_LZ9o"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_networking_result)

        AndroidNetworking.initialize(applicationContext) //초기화

        val okHttpClient = OkHttpClient().newBuilder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()
        AndroidNetworking.initialize(applicationContext, okHttpClient) //okHttp를 네트워킹 계층으로 사용, 클라이언트 생성, 초기화하여 전달

        //Jackson Parser와 함께 fast Android 네트워킹 사용
        AndroidNetworking.setParserFactory(JacksonParserFactory())

        AndroidNetworking.get(url)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.d("RequestResult", "FastAndroidNetworkingExample Type : get, result: onResponse")
                    try {
                        val formattedResult = StringBuilder()
                        val responseJSONArray = response.getJSONArray("results")
                        for (i in 0 until responseJSONArray.length()) {
                            formattedResult.append("${responseJSONArray.getJSONObject(i)["name"]} => ${responseJSONArray.getJSONObject(i)["rating"]} \n")
                        }
                        tv_result.text = "List of Restaurants \nName\tRating \n \n$formattedResult"
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                override fun onError(error: ANError) {
                    Log.d("요청결과", "FastAndroidNetworkingExample Type : get, result : onError $error")
                }
            })//getAsJSONObject
    }
}