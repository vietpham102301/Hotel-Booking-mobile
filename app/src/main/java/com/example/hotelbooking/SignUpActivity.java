package com.example.hotelbooking;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking.constant.Constant;
import com.example.hotelbooking.signup.SignUpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private EditText firstNameEditTxt;
    private EditText lastNameEditTxt;
    private EditText emailEditTxt;
    private EditText userNameEditTxt;
    private EditText passwordEditTxt;
    private EditText bDayEditTxt;
    private EditText genderEditTxt;
    private EditText phoneEditTxt;
    private Button signUpBtn;
    private CheckBox termCheckBox;
    private TextView signInTxtView;

    private static int statusCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_app);

        signUpBtn = findViewById(R.id.btn_signup);
        signInTxtView = findViewById(R.id.txt_signin);
        termCheckBox = findViewById(R.id.check1);

        firstNameEditTxt = findViewById(R.id.edt_fname);
        lastNameEditTxt = findViewById(R.id.edt_lname);
        emailEditTxt = findViewById(R.id.edt_email);
        userNameEditTxt = findViewById(R.id.usernameEditTxt);
        passwordEditTxt = findViewById(R.id.passwordEditTxt);
        bDayEditTxt = findViewById(R.id.birthdayEditTxt);
        genderEditTxt = findViewById(R.id.genderEditTxt);
        phoneEditTxt = findViewById(R.id.phoneEditTxt);


        signUpBtn.setOnClickListener(view -> {
            signUp();
        });

        signInTxtView.setOnClickListener(view -> {
            openSignIn();
        });
    }

    public void signUp(){
        String fName = firstNameEditTxt.getText().toString();
        String lName = lastNameEditTxt.getText().toString();
        String email = emailEditTxt.getText().toString();
        String username = userNameEditTxt.getText().toString();
        String pass = passwordEditTxt.getText().toString();
        String bday = bDayEditTxt.getText().toString();
        String gender = genderEditTxt.getText().toString();
        String phone = phoneEditTxt.getText().toString();

        if(fName.equals("") || lName.equals("") || email.equals("") || username.equals("") || pass.equals("") || bday.equals("") || gender.equals("") || phone.equals("")){
            Toast.makeText(this, "Missing field, Please Check And Try Again!", Toast.LENGTH_SHORT).show();
        }else{
            if(termCheckBox.isChecked()){
                String requestBody = String.format("{\n" +
                        "    \"birthday\": \"%s\",\n" +
                        "    \"email\": \"%s\",\n" +
                        "    \"firstname\": \"%s\",\n" +
                        "    \"gender\": \"%s\",\n" +
                        "    \"lastname\": \"%s\",\n" +
                        "    \"password\": \"%s\",\n" +
                        "    \"phone\": \"%s\",\n" +
                        "    \"username\": \"%s\"\n" +
                        "}", bday, email, fName, gender, lName, pass, phone, username);
                makeRequest("POST", Constant.HOST+"/api/v1/users/signup", requestBody, new RequestCallback() {
                    @Override
                    public void onResponse(int responseCode) {
                        if(responseCode == 201){
                            new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(getApplicationContext(), "Signup successful, please login to continue!", Toast.LENGTH_SHORT).show());
                            openSignIn();
                        }else{
                            new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(getApplicationContext(), "API called fail!", Toast.LENGTH_SHORT).show());
                        }
                        System.out.println("Response code: " + responseCode);
                    }
                });


            }else{
                Toast.makeText(this, "Please agree with the term to continue!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public interface RequestCallback {
        void onResponse(int responseCode);
    }

    protected void makeRequest(String requestType, String u, String requestBody, RequestCallback callback) {
        Thread thread = new Thread(() -> {
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

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    in.close();
                    String apiResponse = response.toString();
                    Log.d("Response", apiResponse);
                    callback.onResponse(responseCode); // Invoke the callback with the response code
                } else {
                    Log.e("ERR", "API call failed with response code: " + responseCode);
                    callback.onResponse(responseCode); // Invoke the callback with the response code
                }
            } catch (Exception e) {
                Log.e("ERR", "API call failed", e);
                callback.onResponse(HttpURLConnection.HTTP_BAD_REQUEST); // Invoke the callback with a default response code
            }
        });
        thread.start();
    }


    public void openSignIn(){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }


}





