package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApiService {

    @GET("/api/users")
    Call<UserResponse> getUsers(@Query("page") int page,
            @Query("per_page") int perPage
    );

}
