package com.example.furnique.models.interfaces;

import com.example.furnique.contracts.SuccessDTO;
import com.example.furnique.dto.request.CartRequestDTO;
import com.example.furnique.dto.response.CartResponseDTO;
import com.example.furnique.dto.response.DataResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface CartAPI {
    @POST("/carts")
    Call<DataResponseDTO<SuccessDTO>> addProductToCart(@Header("Authorization") String authorization, @Body CartRequestDTO.AddToCartDto addToCartDto);

    @GET("/carts")
    Call<CartResponseDTO> getCart(@Header("Authorization") String authorization);

    @PATCH("/carts")
    Call<DataResponseDTO<SuccessDTO>> updateProductQuantityInCart(@Header("Authorization") String authorization, @Body CartRequestDTO.UpdateCartDto updateCartDto);

    @DELETE("/carts")
    Call<DataResponseDTO<SuccessDTO>> removeItemInCart(@Header("Authorization") String authorization, @Body CartRequestDTO.DeleteItemInCartDto deleteItemInCartDto);
}
