package com.example.furnique.models.interfaces;

import com.example.furnique.dto.response.ListResponseDTO;
import com.example.furnique.dto.response.ProductDetailsResponseDTO;
import com.example.furnique.schemas.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductAPI {
    @GET("/products/public")
    Call<ListResponseDTO<Product>> getProducts(@Query("limit") int limit);

    @GET("products/public/{id}")
    Call<ProductDetailsResponseDTO> getProductDetails(@Path("id") String id);
}
