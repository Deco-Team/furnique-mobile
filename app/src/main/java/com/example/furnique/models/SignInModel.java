package com.example.furnique.models;

import android.util.Log;

import com.example.furnique.contracts.Constants;
import com.example.furnique.dto.request.SignInDTO;
import com.example.furnique.dto.response.SignInResponseDTO;
import com.example.furnique.models.interfaces.SignInAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInModel {

    private final SignInDTO signInDTO;

    public SignInModel(SignInDTO signInDTO) {
        this.signInDTO = signInDTO;
    }

    public void signIn() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SignInAPI signInAPI = retrofit.create(SignInAPI.class);
        signInAPI.loginCustomer(signInDTO).enqueue(new Callback<SignInResponseDTO>() {
            @Override
            public void onResponse(Call<SignInResponseDTO> call, Response<SignInResponseDTO> response) {
                Log.d("onResponse", response.body().getData().getAccessToken());
            }

            @Override
            public void onFailure(Call<SignInResponseDTO> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

}
