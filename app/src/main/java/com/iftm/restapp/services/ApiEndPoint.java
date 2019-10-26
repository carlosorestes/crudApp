package com.iftm.restapp.services;

import com.iftm.restapp.data.model.Credentials;
import com.iftm.restapp.data.model.LoggedInUser;
import com.iftm.restapp.data.model.Token;
import com.iftm.restapp.entidades.Post;
import com.iftm.restapp.entidades.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiEndPoint {

    @GET("users")
    Call<List<User>> obterUsuarios();

    @POST("auth/login")
    Call<Token> login(@Body Credentials credentials);

    @GET("users")
    Call<List<User>> getUsers(@Header("Authorization") String authToken);

    @POST("users")
    Call<User> add(@Header("Authorization") String authToken, @Body User user);

}
