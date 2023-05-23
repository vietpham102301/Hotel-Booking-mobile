package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hotelbooking.order.model.HotelOrder;
import com.example.hotelbooking.order.model.OrderResponse;
import com.example.hotelbooking.signin.SignInRes;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {
    private EditText usernameEditTxt;
    private EditText passwordEditTxt;
    private Button signinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_app);

        usernameEditTxt = findViewById(R.id.edt_email);
        passwordEditTxt = findViewById(R.id.password);
        signinBtn = findViewById(R.id.loginbtn);


        signinBtn.setOnClickListener(view -> {
            String username = usernameEditTxt.getText().toString();
            String pass = passwordEditTxt.getText().toString();
            if (username.equals("") || pass.equals("")) {
                Toast.makeText(SignInActivity.this, "Please enter username and password!", Toast.LENGTH_SHORT).show();
            }else{
                new SignInActivity.ApiTask().execute();
            }

        });

    }

    private class ApiTask extends AsyncTask<Void, Void, SignInRes> {
        @Override
        protected SignInRes doInBackground(Void... voids) {
            try {

                SharedPreferences sharedPreferences = getSharedPreferences(PaymentActivity.SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String username = usernameEditTxt.getText().toString();
                String pass = passwordEditTxt.getText().toString();

                String signInRes = "";
                Map<String, List<String>> responseHeaders = new HashMap<>();
                if (username.equals("") || pass.equals("")) {
                    Toast.makeText(SignInActivity.this, "Please enter username and password!", Toast.LENGTH_SHORT).show();
                } else {
                    String requestBody = String.format("{ \"password\": \"%s\", \"username\": \"%s\"}\n", pass, username);

                    signInRes = makeRequest("POST", "http://14.225.255.238/booking/api/v1/users/signin", requestBody, responseHeaders);
                }

                Gson gson = new Gson();

                SignInRes res = null;
                try {
                    res = gson.fromJson(signInRes, SignInRes.class);
                } catch (JsonSyntaxException e) {
                    Log.e("ERR", "Failed to parse JSON", e);
                    Toast.makeText(SignInActivity.this, "Failed to parse JSON response", Toast.LENGTH_LONG).show();
                }

                List<String> authorizationTypeHeader = responseHeaders.get("Authorization");
                if (authorizationTypeHeader != null && !authorizationTypeHeader.isEmpty()) {
                    String authorization = authorizationTypeHeader.get(0);
                    editor.putString("token", authorization);
                    Log.d("Token when login", authorization);
                }

                editor.apply();

                return res;
            } catch (Exception e) {
                Log.e("ERR", "API call failed", e);
                Toast.makeText(SignInActivity.this, "Failed to connect to server please try again later", Toast.LENGTH_LONG).show();
                return null;
            }
        }


        @Override
        protected void onPostExecute(SignInRes res) {
            if (res != null) {
                SharedPreferences sharedPreferences = getSharedPreferences(PaymentActivity.SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("user_id", res.getData().getId());
                editor.putString("customer_name", res.getData().getFirstname() +" "+res.getData().getLastname());
                editor.putString("phone", res.getData().getPhone());
                editor.apply();

                if(res.getData().getRoleId().equals("CUSTOMER")){
                    openHomepage();
                }else if(res.getData().getRoleId().equals("HOTEL")){
                    openHotelAdmin();
                }
            }
        }

        protected String makeRequest(String requestType, String u, String requestBody, Map<String, List<String>> responseHeaders) {
            try {
                URL url = new URL(u);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod(requestType);
                con.setDoOutput(true); // Enable output stream
                con.setRequestProperty("Content-Type", "application/json"); // Set the appropriate content type if using JSON
                Log.d("RequestBody", requestBody);
                // Write request body to the connection's output stream
                if (requestBody != null && !requestBody.isEmpty()) {
                    OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                    writer.write(requestBody);
                    writer.flush();
                    writer.close();
                }
                int responseCode = con.getResponseCode();
                responseHeaders.putAll(con.getHeaderFields());


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
                    new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(getApplicationContext(), "Wrong username or password!", Toast.LENGTH_SHORT).show());
                    Log.e("ERR", "API call failed with response code: " + responseCode);
                }
            } catch (Exception e) {
                Log.e("ERR", "API call failed", e);
            }

            return null;
        }
    }
    public void openHomepage(){
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }
    public void openHotelAdmin(){
        Intent intent = new Intent(this, HotelAdminActivity.class);
        startActivity(intent);
    }
}







