package com.example.furnique.dto.response;

import com.example.furnique.schemas.Product;

import java.util.List;

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

        private List<ItemDto> items;

        private int totalAmount;

        public List<ItemDto> getItems() {
            return items;
        }

        public int getTotalAmount() {
            return totalAmount;
        }
    }

    public class ItemDto {
        private String productId;

        private String sku;

        private int quantity;

        private ProductItemDto product;

        public String getProductId() {
            return productId;
        }

        public String getSku() {
            return sku;
        }

        public int getQuantity() {
            return quantity;
        }

        public ProductItemDto getProduct() {
            return product;
        }

        public class ProductItemDto {
            String name;
            List<String> images;
            List<Product.ProductVariant> variants;

            public String getName() {
                return name;
            }

            public String getFirstImage() {
                return images.get(0);
            }

            public int getFirstVariantPrice() {
                return variants.get(0).getPrice();
            }

            public int getFirstVariantQuantity() {
                return variants.get(0).getQuantity();
            }
        }
    }
}
