package com.example.networklibraryexample.NetworkAPI_Library.RetrofitExamples;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.networklibraryexample.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitExample extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking_result);

        textView = (TextView)findViewById(R.id.tv_result);

        Call<JsonObject> res = NetRetrofit.getInstance().getService().getMyJsonObject();
        res.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("RequestResult","RetrofitExample, Type : get, Result : onResponse");
                try {
                    StringBuilder formattedResult = new StringBuilder();
                    JSONObject jsonObject = new JSONObject(response.body().toString());
                    JSONArray responseJSONArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < responseJSONArray.length(); i++) {
                        formattedResult.append("\n" + responseJSONArray.getJSONObject(i).get("name") + " => \t" + responseJSONArray.getJSONObject(i).get("rating"));
                    }
                    textView.setText("List of Restaurants \n" + " Name" + "\tRating \n" + formattedResult);
                    textView.setText(formattedResult);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("RequestResult","RetrofitExample, Type : get, Result : onFailure, Error Message : " + t.getMessage());
            }
        });
    }
}