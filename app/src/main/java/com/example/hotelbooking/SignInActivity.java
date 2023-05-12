package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelbooking.signin.ApiSignIn;
import com.example.hotelbooking.signin.Appclient;
import com.example.hotelbooking.signin.User;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignInActivity extends AppCompatActivity {
    private TextView txtsign;
    private EditText email;
    private EditText password;
    private Button loginbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_app);
        email = findViewById(R.id.edt_email);
        password =(EditText) findViewById(R.id.password);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        txtsign =  findViewById(R.id.txt_sign);
        txtsign.setOnClickListener(view -> startActivity( new Intent(SignInActivity.this, SignUpActivity.class)));
        //admin and admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get data from textbox
                String strEmail, strPassword;
                strEmail = email.getText().toString();
                strPassword = password.getText().toString();

                if (strEmail == ""){
                    Toast.makeText(SignInActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                }
                if (strPassword == "") {
                    Toast.makeText(SignInActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                }
                String authToken = createAuthToken(email, password);
                checkLoginDetails(authToken);
            }
        });
    }
    private void checkLoginDetails(String authToken) {
        Retrofit retrofit = Appclient.getClient();
        final ApiSignIn apiSignIn = retrofit.create(ApiSignIn.class);

        Call<User> call = apiSignIn.checkUsers(authToken);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    if (response.body().matches("success")){
                        Toast.makeText(SignInActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(SignInActivity.this, "Invalid credential", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("TAG", t.toString());
                t.printStackTrace();
            }
        });
    }

    private String createAuthToken(EditText email, EditText password) {
        byte[] data = new byte[0];
        try {
            data = (email + ":" + password).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Basic " + Base64.encodeToString(data, Base64.NO_WRAP);
    }
}




