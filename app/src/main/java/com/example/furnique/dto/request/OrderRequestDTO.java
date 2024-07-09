package com.example.furnique.dto.request;

import java.util.List;

public class OrderRequestDTO {
    public static class CreateOrderDTO {
        public static class OrderCustomer {
            private String firstName;
            private String lastName;
            private String email;
            private String phone;
            private String shippingAddress;

            public OrderCustomer(String firstName, String lastName, String email, String phone, String shippingAddress) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.phone = phone;
                this.shippingAddress = shippingAddress;
            }
        }

        public static class OrderItem {
            private String productId;
            private String sku;

            public OrderItem(String productId, String sku) {
                this.productId = productId;
                this.sku = sku;
            }
        }

        private OrderCustomer customer;
        private List<OrderItem> items;
        private String paymentMethod;
        private String notes;

        public CreateOrderDTO(OrderCustomer customer, List<OrderItem> items, String paymentMethod, String notes) {
            this.customer = customer;
            this.items = items;
            this.paymentMethod = paymentMethod;
            this.notes = notes;
        }
    }
}
