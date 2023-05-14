package com.example.hotelbooking;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking.pieChart.BookedRoomRes;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PieChartActivity extends AppCompatActivity {
    private ArrayList bookedRoomArr;
    private PieChart BookedRoomChart;
    private String header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        new PieChartActivity.ApiTask().execute();

    }

    private void setData(BookedRoomRes bookedRoomRes){
        bookedRoomArr = new ArrayList();
        float total =0;
        for(int i=0; i<bookedRoomRes.getData().size();i++){
            total += bookedRoomRes.getData().get(i).getQuantity();
        }
        float finalTotal = total;
        bookedRoomRes.getData().forEach(r ->{
            bookedRoomArr.add(new PieEntry((r.getQuantity()/ finalTotal)*100, r.getName()));
        });
    }

    private class ApiTask extends AsyncTask<Void, Void, BookedRoomRes> {
        @Override
        protected BookedRoomRes doInBackground(Void... voids) {
            try {
                saveData();
                showDataToConsole();
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", header);
                headers.put("Content-Type", "application/json");

                String bookedRoomRes = makeRequest("GET","http://14.225.255.238/booking/api/v1/hotels/precent", headers);

                Gson gson = new Gson();

                BookedRoomRes BookedRoom = gson.fromJson(bookedRoomRes, BookedRoomRes.class);

                return BookedRoom;

            } catch (Exception e) {
                Log.e("ERR", "API call failed", e);
                Toast.makeText(PieChartActivity.this, "Failed to connect to server please try again later", Toast.LENGTH_LONG).show();
            }

            return null;
        }

        @Override
        protected void onPostExecute(BookedRoomRes bookedRoomRes) {
            if (bookedRoomRes != null) {
                setData(bookedRoomRes);
                BookedRoomChart = findViewById(R.id.pieChart);
                PieDataSet bookedRoomDataSet = new PieDataSet(bookedRoomArr, "bookedRoom");
                PieData bookedRoomData = new PieData(bookedRoomDataSet);
                BookedRoomChart.setData(bookedRoomData);

                bookedRoomDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                bookedRoomDataSet.setValueTextColor(Color.BLACK);
                bookedRoomDataSet.setValueTextSize(16f);
                BookedRoomChart.getDescription().setEnabled(false);

                // Invalidate the chart and animate it
                BookedRoomChart.invalidate();
                BookedRoomChart.animateY(1000);
                BookedRoomChart.setCenterText("Booked Room (%)");
                BookedRoomChart.animate();

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

    public void saveData(){
        android.content.SharedPreferences sharedPreferences = getSharedPreferences(PaymentActivity.SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("header", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4IiwiaWF0IjoxNjgzNDI4NzczLCJleHAiOjE2ODM1OTU0MTh9.wWyU2kK42JT4Ds3AgZxvTRlYPRljXAhnnMKDf3Yr-7GIgXc-xiqTRgKvpmQDMGJ3iQGKQp5g0cUuxLbXNqpJUg");
        editor.apply();
    }

    public void showDataToConsole(){
        SharedPreferences sharedPreferences = getSharedPreferences(PaymentActivity.SHARED_PREFS,MODE_PRIVATE);
        header = sharedPreferences.getString("header", "");
    }
}