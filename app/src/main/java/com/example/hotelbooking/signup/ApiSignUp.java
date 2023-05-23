package com.example.hotelbooking.signup;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiSignUp {
    @FormUrlEncoded
    @POST("api/v1/users/signup")
    Call<ResponseBody> createUser(
            @Field("email") String email,
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("password") String password,
            @Field("username") String username,
            @Field("phone") String phone,
            @Field("gender") String gender,
            @Field("birthday") String birthday
            );
}
