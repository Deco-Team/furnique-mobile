package com.example.furnique.contracts;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;

public class Utils {
    public static String decoded(String JWTEncoded) throws Exception {
        try {
            String[] split = JWTEncoded.split("\\.");
            String decodedString = getJson(split[1]);
            Log.d("Utils.decoded", "Body: " + decodedString);
            return decodedString;
        } catch (UnsupportedEncodingException e) {
            //Error
            return "{}";
        }
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException{
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }
}
