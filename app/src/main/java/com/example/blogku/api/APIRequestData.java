package com.example.blogku.api;

import com.example.blogku.model.PostList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET("json.php")
    Call<PostList> ardRetrieveData();
}