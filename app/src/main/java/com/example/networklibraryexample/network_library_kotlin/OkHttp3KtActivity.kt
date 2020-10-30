package com.example.networklibraryexample.network_library_kotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.networklibraryexample.R
import kotlinx.android.synthetic.main.activity_networking_result.*
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class OkHttp3KtActivity: AppCompatActivity(R.layout.activity_networking_result) {

    private val url =
            "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyBrJ3ec9wTuS6L-xHkaXLU8BJbFsx_LZ9o"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val client = OkHttpClient()
        val request = Request.Builder()
                .get()
                .url(url)
                .build()
        //비동기 처리 enqueue사용
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                Log.d("RequestResult", "OkHttp3 onResponse : ${response.code}" )

                val myResponse = response.body!!.string()
                runOnUiThread {
                    try {
                        val formattedResult = StringBuilder()
                        val jsonObject = JSONObject(myResponse)
                        val responseJSONArray = jsonObject.getJSONArray("results")
                        for (i in 0 until responseJSONArray.length()) {
                            formattedResult.append("${responseJSONArray.getJSONObject(i)["name"]} => ${responseJSONArray.getJSONObject(i)["rating"]} \n")
                        }
                        tv_result.text = "List of Restaurants \nName\tRating \n \n$formattedResult"
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.d("RequestResult", "onFailure")
                call.cancel()
            }

        })
    }
}