package com.example.andproject.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.andproject.adapters.RetrofitServiceGenerator;
import com.example.andproject.models.Advice;
import com.example.andproject.models.AdviceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdviceRepository {

    private static AdviceRepository instance;
    MutableLiveData<Advice> advice;

    private AdviceRepository() {
        advice = new MutableLiveData<>();
    }

    public MutableLiveData<Advice> adviceLiveData() {
        return advice;
    }

    public static synchronized AdviceRepository getInstance() {
        if (instance == null) {
            instance = new AdviceRepository();
        }
        return instance;
    }

    public void fetchAdvice() {
        AdviceApi adviceApi = RetrofitServiceGenerator.getAdviceApi();
        Call<Advice> call = adviceApi.getAdvice();
        call.enqueue(new Callback<Advice>() {
            @Override
            public void onResponse(Call<Advice> call, Response<Advice> response) {
                if (response.isSuccessful()) {
                    advice.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Advice> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }
}
