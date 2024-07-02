package com.example.furnique.models;

import android.util.Log;

import com.example.furnique.contracts.Constants;
import com.example.furnique.dto.request.OrderRequestDTO;
import com.example.furnique.dto.response.DataResponseDTO;
import com.example.furnique.dto.response.OrderResponseDTO;
import com.example.furnique.models.interfaces.OrderAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckoutModel {
    interface CreateOrderCallback {
        void onSuccess();

        void onFail();
    }

    public CheckoutModel() {
    }

    public void createOrder(String accessToken, OrderRequestDTO.CreateOrderDTO createOrderDTO, CreateOrderCallback createOrderCallback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderAPI orderAPI = retrofit.create(OrderAPI.class);
        orderAPI.createOrder("Bearer " + accessToken, createOrderDTO).enqueue(new Callback<DataResponseDTO<OrderResponseDTO.CreateOrderDTO>>() {
            @Override
            public void onResponse(Call<DataResponseDTO<OrderResponseDTO.CreateOrderDTO>> call, Response<DataResponseDTO<OrderResponseDTO.CreateOrderDTO>> response) {
                if (response.isSuccessful()) {
                    createOrderCallback.onSuccess();
                } else {
                    createOrderCallback.onFail();
                }
            }

            @Override
            public void onFailure(Call<DataResponseDTO<OrderResponseDTO.CreateOrderDTO>> call, Throwable t) {
                Log.e(CheckoutModel.class.getName(), "Create Order", t);
            }
        });
    }
}
