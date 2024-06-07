package com.example.furnique.dto.response;

import com.example.furnique.schemas.Product;

public class ProductDetailsResponseDTO {
    Product data;

    public ProductDetailsResponseDTO(Product data) {
        this.data = data;
    }

    public Product getData() {
        return data;
    }
}
