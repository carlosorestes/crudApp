package com.iftm.restapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.iftm.restapp.R;
import com.iftm.restapp.entidades.Post;
import com.iftm.restapp.entidades.User;
import com.iftm.restapp.services.RetrofitService;
import com.iftm.restapp.services.UtilsService;
import com.iftm.restapp.ui.login.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private static final String LOG_TAG = PostActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

    }


    public void sendUser(View view){

        final EditText usernameEditText = findViewById(R.id.name);
        final EditText emailEditText = findViewById(R.id.username);
        final EditText phoneEditText = findViewById(R.id.phone);
        final EditText passwordEditText = findViewById(R.id.password);

        User user = new User();
        user.setName(usernameEditText.getText().toString());
        user.setEmail(emailEditText.getText().toString());
        user.setPhone(phoneEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());

        RetrofitService.getServico().add(UtilsService.getInstance().getToken(), user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    Toast.makeText(PostActivity.this, "Sucess: User " + response.body().getName(), Toast.LENGTH_SHORT).show();
                    launchMainActivity();

                } else {
                    Toast.makeText(PostActivity.this, "error :{ Make login", Toast.LENGTH_SHORT).show();
                    launchMainActivity();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("addUser", t.getStackTrace().toString());
            }
        });
    }

    private void launchMainActivity() {
        Log.d(LOG_TAG, "launchMainActivity");
        startActivity(new Intent(this, MainActivity.class));
    }
}
