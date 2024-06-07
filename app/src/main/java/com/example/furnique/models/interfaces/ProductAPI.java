package com.example.furnique.models.interfaces;

import com.example.furnique.dto.response.ListResponseDTO;
import com.example.furnique.dto.response.ProductDetailsResponseDTO;
import com.example.furnique.schemas.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductAPI {
    @GET("/products/public?limit=50")
    Call<ListResponseDTO<Product>> getProducts();

    @GET("products/public/{id}")
    Call<ProductDetailsResponseDTO> getProductDetails(@Path("id") String id);
}
