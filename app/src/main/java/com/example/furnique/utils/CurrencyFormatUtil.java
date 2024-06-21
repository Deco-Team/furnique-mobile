package com.example.furnique.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatUtil {
    public static String formatAsVietnamDong(double amount) {
        Locale vietnamLocale = new Locale("vi", "VN");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(vietnamLocale);
        return currencyFormat.format(amount);
    }
}
