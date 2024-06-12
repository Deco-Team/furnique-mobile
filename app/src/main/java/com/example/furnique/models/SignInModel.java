package com.example.furnique.models;

import android.util.Log;

import com.example.furnique.activities.SignInActivity;
import com.example.furnique.contracts.Constants;
import com.example.furnique.dto.request.SignInDTO;
import com.example.furnique.dto.response.SignInResponseDTO;
import com.example.furnique.models.interfaces.SignInAPI;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInModel {

    private final SignInDTO signInDTO;
    private final SignInActivity signInActivity;

    public SignInModel(SignInActivity signInActivity, SignInDTO signInDTO) {
        this.signInActivity = signInActivity;
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
                Log.d("signIn.onResponse: ", String.valueOf(response.code()));
                Log.d("signIn.onResponse: ", new Gson().toJson(response.body()));
                if(response.code() == 201) {
                    Log.d("signIn.onResponse: ", "Success");
                    signInActivity.onLoginSuccess(response.body().getData().getAccessToken());
                } else {
                    Log.d("signIn.onResponse: ", "Invalid username or password");
                    signInActivity.onLoginFailed();
                }
            }

            @Override
            public void onFailure(Call<SignInResponseDTO> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

}
