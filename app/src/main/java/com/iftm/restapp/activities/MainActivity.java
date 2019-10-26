package com.iftm.restapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.iftm.restapp.R;
import com.iftm.restapp.entidades.User;
import com.iftm.restapp.services.RetrofitService;
import com.iftm.restapp.services.UtilsService;
import com.iftm.restapp.ui.login.LoginActivity;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private final LinkedList<User> listUser = new LinkedList<>();

    private TextView campo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        campo = findViewById(R.id.viewJSON);
        getUsers();

    }

    private void getUsers() {
        RetrofitService.getServico().getUsers(UtilsService.getInstance().getToken()).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    listUser.addAll(response.body());
                    for (User user : listUser) {
                        campo.append("\n\nid: " + user.getId() +
                                "\nNome: " + user.getName() +
                                "\nEmail: " + user.getEmail());
                    }
                } else {
                    Toast.makeText(MainActivity.this, "error :{ Make login", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("RestApp", t.getStackTrace().toString());
            }
        });
    }

    public void navegar(View view) {
        startActivity(new Intent(this, PostActivity.class));
    }
}
