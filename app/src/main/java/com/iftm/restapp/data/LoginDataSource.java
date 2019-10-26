package com.iftm.restapp.data;

import android.util.Log;
import android.widget.TextView;

import com.iftm.restapp.R;
import com.iftm.restapp.data.model.Credentials;
import com.iftm.restapp.data.model.LoggedInUser;
import com.iftm.restapp.data.model.Token;
import com.iftm.restapp.entidades.Post;
import com.iftm.restapp.services.RetrofitService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {


    private Result<LoggedInUser> loggedInUser;

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
//            Credentials credentials = new Credentials(username, password);

//            authenticate(credentials);

            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe",
                            "");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

    private void authenticate(final Credentials credentials) {


        RetrofitService.getServico().login(credentials).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                credentials.setToken(response.body().getToken());
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });

    }
}
