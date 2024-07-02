package com.example.furnique.models.interfaces;


import com.example.furnique.dto.request.OrderRequestDTO;
import com.example.furnique.dto.response.DataResponseDTO;
import com.example.furnique.dto.response.OrderResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OrderAPI {
    @POST("/orders")
    Call<DataResponseDTO<OrderResponseDTO.CreateOrderDTO>> createOrder(@Header("Authorization") String authorization, @Body() OrderRequestDTO.CreateOrderDTO createOrderDto);
}
