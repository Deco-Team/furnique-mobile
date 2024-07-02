package com.example.furnique.dto.response;

public class OrderResponseDTO {
    public static class CreateOrderDTO {
        private String bin;
        private String accountNumber;
        private String accountName;
        private double amount;
        private String description;
        private int orderCode;
        private String currency;
        private String paymentLinkId;
        private String status;
        private String checkoutUrl;
        private String qrCode;

        public CreateOrderDTO(String bin, String accountNumber, String accountName, double amount, String description, int orderCode, String currency, String paymentLinkId, String status, String checkoutUrl, String qrCode) {
            this.bin = bin;
            this.accountNumber = accountNumber;
            this.accountName = accountName;
            this.amount = amount;
            this.description = description;
            this.orderCode = orderCode;
            this.currency = currency;
            this.paymentLinkId = paymentLinkId;
            this.status = status;
            this.checkoutUrl = checkoutUrl;
            this.qrCode = qrCode;
        }

        public String getBin() {
            return bin;
        }

        public void setBin(String bin) {
            this.bin = bin;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(int orderCode) {
            this.orderCode = orderCode;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getPaymentLinkId() {
            return paymentLinkId;
        }

        public void setPaymentLinkId(String paymentLinkId) {
            this.paymentLinkId = paymentLinkId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCheckoutUrl() {
            return checkoutUrl;
        }

        public void setCheckoutUrl(String checkoutUrl) {
            this.checkoutUrl = checkoutUrl;
        }

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }
    }
}
