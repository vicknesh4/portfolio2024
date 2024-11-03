package com.restaurant.restaurant.util;
import java.util.Base64;

public class ImageUtils {
    public static String encodeImageToBase64(byte[] imageData) {
        return Base64.getEncoder().encodeToString(imageData);
    }
}