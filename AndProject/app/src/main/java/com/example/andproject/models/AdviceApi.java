package com.example.andproject.models;


import retrofit2.Call;
import retrofit2.http.GET;

public interface AdviceApi {
    @GET("advice")
    Call<Advice> getAdvice();
}
