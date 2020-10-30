package com.example.networklibraryexample.NetworkAPI_Library;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.networklibraryexample.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttpExample extends AppCompatActivity {
    TextView txtString;

    private String url =
            "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyBrJ3ec9wTuS6L-xHkaXLU8BJbFsx_LZ9o";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking_result);

        txtString = (TextView) findViewById(R.id.tv_result);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() { // OkHttpClient에 사용자가 정의한 Requst Call
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("RequestResult","onFailure");
                call.cancel();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string(); // 응답된 Json Data의 Body를 저장
                OKHttpExample.this.runOnUiThread(new Runnable() { // 버튼을 누르면 핸들러가 없기 때문에 MainThread에서 UI 작업을 실행해야한다.
                    @Override
                    public void run() {
                        Log.d("RequestResult","OkHttpExample onResponse");
                        try {
                            StringBuilder formattedResult = new StringBuilder(); // TextView에 set할 StringBuilder 선언
                            JSONObject jsonObject = new JSONObject(myResponse);
                            JSONArray responseJSONArray = jsonObject.getJSONArray("results");
                            for (int i = 0; i < responseJSONArray.length(); i++) {
                                formattedResult.append("\n" + responseJSONArray.getJSONObject(i).get("name") + " => \t" + responseJSONArray.getJSONObject(i).get("rating"));
                            }
                            txtString.setText("List of Restaurants \n" + "Name" + "\tRating \n" + formattedResult);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
