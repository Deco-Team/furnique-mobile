package com.example.furnique.models;

import android.util.Log;

import com.example.furnique.contracts.Constants;
import com.example.furnique.contracts.SuccessDTO;
import com.example.furnique.dto.request.CartRequestDTO;
import com.example.furnique.dto.response.CartResponseDTO;
import com.example.furnique.dto.response.DataResponseDTO;
import com.example.furnique.models.interfaces.CartAPI;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartModel {
    public CartModel() {
    }

    public void addProductToCart(String accessToken, CartRequestDTO.AddToCartDto addToCartDto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CartAPI cartAPI = retrofit.create(CartAPI.class);
        cartAPI.addProductToCart("Bearer " + accessToken, addToCartDto).enqueue(new Callback<DataResponseDTO<SuccessDTO>>() {
            @Override
            public void onResponse(Call<DataResponseDTO<SuccessDTO>> call, Response<DataResponseDTO<SuccessDTO>> response) {
                Log.d("addProductToCart.onResponse: ", String.valueOf(response.code()));
                Log.d("addProductToCart.onResponse: ", new Gson().toJson(response.body()));
                if(response.code() == 200) {
                    Log.d("addProductToCart.onResponse: ", "Success");
                } else {
                    Log.d("addProductToCart.onResponse: ", "Failed");
                }
            }
            @Override
            public void onFailure(Call<DataResponseDTO<SuccessDTO>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    public void getCart(String accessToken) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CartAPI cartAPI = retrofit.create(CartAPI.class);
        cartAPI.getCart("Bearer " + accessToken).enqueue(new Callback<CartResponseDTO>() {
            @Override
            public void onResponse(Call<CartResponseDTO> call, Response<CartResponseDTO> response) {
                Log.d("getCart.onResponse: ", String.valueOf(response.code()));
                Log.d("getCart.onResponse: ", new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<CartResponseDTO> call, Throwable t) {
                System.out.println("Error when fetching cart data");
                System.out.println(t);
            }
        });
    }

    public void updateProductQuantityInCart(String accessToken, CartRequestDTO.UpdateCartDto updateCartDto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CartAPI cartAPI = retrofit.create(CartAPI.class);
        cartAPI.updateProductQuantityInCart("Bearer " + accessToken, updateCartDto).enqueue(new Callback<DataResponseDTO<SuccessDTO>>() {
            @Override
            public void onResponse(Call<DataResponseDTO<SuccessDTO>> call, Response<DataResponseDTO<SuccessDTO>> response) {
                Log.d("updateProductQuantityInCart.onResponse: ", String.valueOf(response.code()));
                Log.d("updateProductQuantityInCart.onResponse: ", new Gson().toJson(response.body()));
                if(response.code() == 200) {
                    Log.d("updateProductQuantityInCart.onResponse: ", "Success");
                } else {
                    Log.d("updateProductQuantityInCart.onResponse: ", "Failed");
                }
            }
            @Override
            public void onFailure(Call<DataResponseDTO<SuccessDTO>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    public void removeItemInCart(String accessToken, CartRequestDTO.DeleteItemInCartDto deleteItemInCartDto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CartAPI cartAPI = retrofit.create(CartAPI.class);
        cartAPI.removeItemInCart("Bearer " + accessToken, deleteItemInCartDto).enqueue(new Callback<DataResponseDTO<SuccessDTO>>() {
            @Override
            public void onResponse(Call<DataResponseDTO<SuccessDTO>> call, Response<DataResponseDTO<SuccessDTO>> response) {
                Log.d("removeItemInCart.onResponse: ", String.valueOf(response.code()));
                Log.d("removeItemInCart.onResponse: ", new Gson().toJson(response.body()));
                if(response.code() == 200) {
                    Log.d("removeItemInCart.onResponse: ", "Success");
                } else {
                    Log.d("removeItemInCart.onResponse: ", "Failed");
                }
            }
            @Override
            public void onFailure(Call<DataResponseDTO<SuccessDTO>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

}
