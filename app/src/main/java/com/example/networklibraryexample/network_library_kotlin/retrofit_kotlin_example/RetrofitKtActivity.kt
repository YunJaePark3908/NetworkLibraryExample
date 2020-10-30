package com.example.networklibraryexample.network_library_kotlin.retrofit_kotlin_example

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.networklibraryexample.R
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_networking_result.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitKtActivity: AppCompatActivity(R.layout.activity_networking_result) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val res: Call<JsonObject?>? = RetrofitKtClient.ourInstance.service.getMyJsonObject()
        res?.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                try {
                    val formattedResult = StringBuilder()
                    val jsonObject = JSONObject(response.body().toString())
                    val responseJSONArray = jsonObject.getJSONArray("results")
                    for (i in 0 until responseJSONArray.length()) {
                        formattedResult.append("${responseJSONArray.getJSONObject(i)["name"]} => ${responseJSONArray.getJSONObject(i)["rating"]} \n")
                    }
                    tv_result.text = "List of Restaurants \nName\tRating \n \n$formattedResult"

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<JsonObject?>, t: Throwable) {
                Log.d("RequestResult", "RetrofitExample, Type : get, Result : onFailure, Error Message : " + t.message)
            }
        })
    }
}