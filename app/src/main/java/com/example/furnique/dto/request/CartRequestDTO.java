package com.example.furnique.dto.request;

public class CartRequestDTO {
    public class AddToCartDto {
        private String productId;

        private String sku;

        private int quantity;

        public AddToCartDto(String productId, String sku, int quantity) {
            this.productId = productId;
            this.sku = sku;
            this.quantity = quantity;
        }
    }

    public class UpdateCartDto {
        private String productId;

        private String sku;

        private int quantity;

        public UpdateCartDto(String productId, String sku, int quantity) {
            this.productId = productId;
            this.sku = sku;
            this.quantity = quantity;
        }
    }

    public class DeleteItemInCartDto {
        private String productId;

        private String sku;

        public DeleteItemInCartDto(String productId, String sku) {
            this.productId = productId;
            this.sku = sku;
        }
    }
}
