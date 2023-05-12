package com.example.hotelbooking;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking.signup.SignUpClient;

import java.io.IOException;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private EditText emailSignUp;
    private EditText passwordSignUp;
    private EditText lastName;
    private EditText firstName;
    private CheckBox checkBox;
    private TextView txt_signIn;
    private Button signUp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_app);

        emailSignUp = findViewById(R.id.edt_email);
        passwordSignUp = findViewById(R.id.password_signup);
        lastName = findViewById(R.id.edt_lname);
        firstName = findViewById(R.id.edt_fname);
        checkBox = findViewById(R.id.check1);
//        signUp = findViewById(R.id.btn_signup);
        findViewById(R.id.btn_signup).setOnClickListener(this::onClick);
        findViewById(R.id.txt_signin).setOnClickListener(this::onClick);


        txt_signIn = findViewById(R.id.txt_sign);
//        txt_signIn.setOnClickListener(view -> startActivity(new Intent(SignUpActivity.this, SignInActivity.class)));
    }
     private void userSignUp(){
        String lastname = lastName.getText().toString().trim();
        String firstname = firstName.getText().toString().trim();
        String email = emailSignUp.getText().toString().trim();
        String password = passwordSignUp.getText().toString().trim();

        if (email.isEmpty()){
            emailSignUp.setError("Email is required");
            emailSignUp.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailSignUp.setError("Enter a valid email");
            emailSignUp.requestFocus();
            return;
        }

         if (password.isEmpty()){
             passwordSignUp.setError("Password required");
             passwordSignUp.requestFocus();
             return;
         }

         if (password.length() < 6){
             passwordSignUp.setError("Password should be atleast 6 character long");
             passwordSignUp.requestFocus();
             return;
         }

         if (firstname.isEmpty()){
             firstName.setError("Firstname required");
             firstName.requestFocus();
             return;
         }

         if (lastname.isEmpty()){
             lastName.setError("Lastname required");
             lastName.requestFocus();
             return;
         }

         Call<ResponseBody> call = SignUpClient
                 .getInstance()
                 .getApi()
                 .createUser(email, password, lastname, firstname);

         call.enqueue(new Callback<ResponseBody>() {
             @Override
             public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                 try {
                     if(response.body() != null) {
                         String s = response.body().string();
                         Toast.makeText(SignUpActivity.this, s, Toast.LENGTH_SHORT).show();
                     }
                 } catch (IOException e){
                     e.printStackTrace();
                 }
             }

             @Override
             public void onFailure(Call<ResponseBody> call, Throwable t) {
                 Toast.makeText(SignUpActivity.this, "", Toast.LENGTH_SHORT).show();
             }
         });
     }

      public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_signup:
                userSignUp();
                break;
            case R.id.txt_signin:

                break;
        }
      }
}
