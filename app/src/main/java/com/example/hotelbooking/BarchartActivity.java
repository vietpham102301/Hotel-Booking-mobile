package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.hotelbooking.barChart.model.BarChartData;
import com.example.hotelbooking.barChart.model.RevenueRes;
import com.example.hotelbooking.constant.Constant;
import com.example.hotelbooking.order.adapter.HotelRecViewAdapter;
import com.example.hotelbooking.order.model.Hotel;
import com.example.hotelbooking.order.model.HotelOrder;
import com.example.hotelbooking.order.model.HotelResposne;
import com.example.hotelbooking.order.model.Order;
import com.example.hotelbooking.order.model.OrderResponse;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BarchartActivity extends AppCompatActivity {
    private ArrayList revenueArr;
    private BarChart revenueBarchart;
    private String header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchat);

        new BarchartActivity.ApiTask().execute();

    }

    private void setData(RevenueRes revenueRes){
        revenueArr = new ArrayList();
        for(int i=1; i<=12; i++){
            int flag = 0;
            for(BarChartData b: revenueRes.getData()){
                Integer month = Integer.parseInt(b.getMonth().split("-")[0]);
                if(month == i){
                    revenueArr.add(new BarEntry(i, (b.getTotal()/1E6f)));
                    flag = 1;
                    break;
                }
            }
                if(flag == 0){
                    revenueArr.add(new BarEntry(i, 0));
                }
        }


    }

    private class ApiTask extends AsyncTask<Void, Void, RevenueRes> {
        @Override
        protected RevenueRes doInBackground(Void... voids) {
            try {
//                saveData();
                showDataToConsole();
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", header);
                headers.put("Content-Type", "application/json");

                String revenueRes = makeRequest("GET", Constant.HOST+"/api/v1/hotels/revenue", headers);

                Gson gson = new Gson();

                RevenueRes revenue = gson.fromJson(revenueRes, RevenueRes.class);

               return revenue;

            } catch (Exception e) {
                Log.e("ERR", "API call failed", e);
                Toast.makeText(BarchartActivity.this, "Failed to connect to server please try again later", Toast.LENGTH_LONG).show();
            }

            return null;
        }

        @Override
        protected void onPostExecute(RevenueRes revenueRes) {
            if (revenueRes != null) {
                setData(revenueRes);
                revenueBarchart = findViewById(R.id.barChart);
                BarDataSet revenueDataSet = new BarDataSet(revenueArr, "Revenue");
                BarData revenueData = new BarData(revenueDataSet);
                revenueBarchart.setData(revenueData);

                revenueDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

                revenueDataSet.setValueTextColor(Color.BLACK);
                revenueDataSet.setValueTextSize(16f);
                revenueBarchart.getDescription().setEnabled(false);

                // Invalidate the chart and animate it
                revenueBarchart.invalidate();
                revenueBarchart.animateY(1000);

            }
        }

        protected String makeRequest(String requestType, String u, Map<String, String> headers){
            try {
                URL url = new URL(u);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod(requestType);
                //set the header
                if (headers != null) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        con.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                int responseCode = con.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    in.close();
                    String apiResponse = response.toString();
                    return apiResponse;
                } else {
                    Log.e("ERR", "API call failed with response code: " + responseCode);
                }
            } catch (Exception e) {
                Log.e("ERR", "API call failed", e);
            }

            return null;
        }
    }

//    public void saveData(){
//        android.content.SharedPreferences sharedPreferences = getSharedPreferences(PaymentActivity.SHARED_PREFS,MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("header", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4IiwiaWF0IjoxNjgzNTU3MzAwLCJleHAiOjE2ODM2OTIyOTh9.hUQCc-hOzlatqReYSVUX2lblEePRyqp-XPuh2RIRuzRP6OE0fXS_2_dkGE56saw4HWogsdB4frA-HrQhX7TDVg");
//        editor.apply();
//    }

    public void showDataToConsole(){
        SharedPreferences sharedPreferences = getSharedPreferences(PaymentActivity.SHARED_PREFS,MODE_PRIVATE);
        header = sharedPreferences.getString("token", "");
    }
}