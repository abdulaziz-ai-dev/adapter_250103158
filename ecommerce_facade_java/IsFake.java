package com.ecommerce;

public class IsFake {

    public boolean isFake(String customerId, double amount) {
        if (customerId == null || customerId.isEmpty()) {
            return true;
        }

        if (amount <= 0 || amount > 10000) {
            return true;
        }

        return false;
    }
}