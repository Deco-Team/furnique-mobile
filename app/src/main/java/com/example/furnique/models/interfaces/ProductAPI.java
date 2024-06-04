package com.example.furnique.models.interfaces;

import com.example.furnique.dto.response.ListResponseDTO;
import com.example.furnique.schemas.Product;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductAPI {
    @GET("/products/public?limit=50")
    Call<ListResponseDTO<Product>> getProducts();
}
