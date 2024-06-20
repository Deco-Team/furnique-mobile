package com.example.furnique.dto.response;

import com.example.furnique.schemas.Product;

public class CartResponseDTO {
    CartDTO data;

    public CartResponseDTO(CartDTO data) {
        this.data = data;
    }

    public CartDTO getData() {
        return data;
    }

    public class CartDTO {
        private String _id;

        private ItemDto[] items;

        private int totalAmount;
    }

    public class ItemDto {
        private String productId;

        private String sku;

        private int quantity;

        private Product Product;
    }
}
