package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;  // Import ArrayList
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private ArrayList<User> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Client.getAPIInterface().getUsers(2, 6)
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.isSuccessful()) {
                            UserResponse userResponse = response.body();
                            if (userResponse != null && userResponse.getUsers() != null) {
                                if (userList == null) {
                                    userList = new ArrayList<>();
                                }
                                userList.clear(); // Clear existing data before adding new data
                                userList.addAll(userResponse.getUsers());
                                updateRecyclerView();
                            }
                        } else {
                            Log.e("API_REQUEST_FAILURE", "Error: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Log.e("API_REQUEST_FAILURE", "Error: " + t.getMessage());

                    }
                });
    }
    @SuppressLint("NotifyDataSetChanged")
    private void updateRecyclerView() {
        if (userAdapter == null) {
            userAdapter = new UserAdapter((Context) MainActivity.this, (ArrayList<User>) userList);
            recyclerView.setAdapter(userAdapter);
        } else {
            userAdapter.notifyDataSetChanged();
        }
    }
}
