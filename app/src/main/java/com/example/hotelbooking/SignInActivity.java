package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelbooking.signin.ApiSignIn;
import com.example.hotelbooking.signin.Appclient;
import com.example.hotelbooking.signin.User;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignInActivity extends AppCompatActivity {
    public static final String SHARED_PREFS = "bookingApp";
    public static final String REMEMBER = "remember";
    public static final String PASS = "passKey";
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String TOKEN ="token";
    SharedPreferences sharedPreferences;
    private Integer userID;
    private TextView txtsign;
    private EditText username;
    private EditText password;
    private Button loginbtn;
    CheckBox cbRemember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_app);
        username = findViewById(R.id.edt_email);
        password =(EditText) findViewById(R.id.password);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        cbRemember= findViewById(R.id.cbRemember);
        txtsign =  findViewById(R.id.txt_sign);
        txtsign.setOnClickListener(view -> startActivity( new Intent(SignInActivity.this, SignUpActivity.class)));


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        userID = sharedPreferences.getInt(USER_ID, 0);
        username.setText(sharedPreferences.getString(USERNAME, ""));
        password.setText(sharedPreferences.getString(PASS, "123456"));
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbRemember.isChecked())
                    //lưu lại thông tin đăng nhập
                    saveData(username.getText().toString(), password.getText().toString());
                else
                    clearData();//xóa thông tin đã lưu
                //nếu thông tin đăng nhập đúng thì đến màng hình home
                if (username.getText().toString().equals(username) && password.getText().toString().equals(password)){
                    Intent intent = new Intent(SignInActivity.this, HomePageActivity.class);
                    startActivity(intent);
                } else
                    Toast.makeText(SignInActivity.this, "", Toast.LENGTH_SHORT).show();
                String authToken = createAuthToken(username, password);
                checkLoginDetails(authToken);
            }
        });
    }

    private void clearData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    private void saveData(String email, String Pass) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, email);
        editor.putString(PASS, Pass);
        editor.putBoolean(REMEMBER,cbRemember.isChecked());
        editor.putInt(USER_ID, 6);
        editor.commit();
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

    private String createAuthToken(EditText username, EditText password) {
        byte[] data = new byte[0];
        try {
            data = (username + ":" + password).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Basic " + Base64.encodeToString(data, Base64.NO_WRAP);
    }
}




