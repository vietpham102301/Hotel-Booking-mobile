package com.example.hotelbooking.signup;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpClient {
    private static final String BASE_URL = "http://14.225.255.238/booking/";
    private static SignUpClient mInstance;
    private Retrofit retrofit;

    private SignUpClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized SignUpClient getInstance(){
        if (mInstance == null){
            mInstance = new SignUpClient();
        }
        return mInstance;
    }

    public ApiSignUp getApi(){
        return retrofit.create(ApiSignUp.class);
    }

}
