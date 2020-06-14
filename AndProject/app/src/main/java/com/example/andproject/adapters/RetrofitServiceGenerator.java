package com.example.andproject.adapters;

import com.example.andproject.models.AdviceApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceGenerator {

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://api.adviceslip.com")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static AdviceApi adviceApi = retrofit.create(AdviceApi.class);

    public static AdviceApi getAdviceApi() {
        return adviceApi;
    }
}
