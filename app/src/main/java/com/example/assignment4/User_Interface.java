package com.example.assignment4;

import java.util.List;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface User_Interface {
    @POST("users/login")
    Call<ResponseBody> userLogin(@Body User user);

    @GET("items")
    Call<List<Items>> getItems(@Header("Cookie") String cook);

//    @GET("heroes")
//    Call<List<User>> getUsers(@Header("Cookie") String cook);


}
