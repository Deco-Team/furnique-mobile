package com.example.furnique.dto.response;

public class OrderResponseDTO {
    public static class CreateOrderDTO {
        private String partnerCode;
        private String requestId;
        private String orderId;
        private double amount;
        private double responseTime;
        private String message;
        private int resultCode;
        private String payUrl;
        private String shortLink;
        private String deeplink;

        public CreateOrderDTO(String partnerCode, String requestId, String orderId, double amount, int responseTime, String message, int resultCode, String payUrl, String shortLink, String deeplink) {
            this.partnerCode = partnerCode;
            this.requestId = requestId;
            this.orderId = orderId;
            this.amount = amount;
            this.responseTime = responseTime;
            this.message = message;
            this.resultCode = resultCode;
            this.payUrl = payUrl;
            this.shortLink = shortLink;
            this.deeplink = deeplink;
        }

        public String getDeeplink() {
            return deeplink;
        }
    }
}
