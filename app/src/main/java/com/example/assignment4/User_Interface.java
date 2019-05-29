package com.example.assignment4;

import com.example.assignment4.models.Items;
import com.example.assignment4.models.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface User_Interface {
    @POST("users/login")
    Call<ResponseBody> userLogin(@Body User user);

    @POST("users/signup")
    Call<ResponseBody> userSignup(@Body User user);

    @GET("items")
    Call<List<Items>> getItems(@Header("Cookie") String cook);
}
