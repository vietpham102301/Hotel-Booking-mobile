package com.example.hotelbooking.signin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiSignIn {
    @FormUrlEncoded
    @POST("api/v1/users/signin")
//    Call<User> checkUsers(
//            @Field("email") String email,
//            @Field("password") String password
//    );

    Call<User> checkUsers(@Header("Authorization")String authToken);
}

//    Gson gson = new GsonBuilder()
//            .setDateFormat("yyyy-MM-dd HH:mm:ss")
//            .create();
//
//    ApiSignIn apiSignIn = new Retrofit.Builder()
//            .baseUrl("http://14.225.255.238/booking/")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//            .create(ApiSignIn.class);


