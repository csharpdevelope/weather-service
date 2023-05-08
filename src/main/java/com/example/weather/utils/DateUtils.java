package com.example.weather.utils;

import java.util.Date;

public final class DateUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    public static boolean afterCurrentDate(Date expiration) {
        return new Date().after(expiration);
    }

    public static String patternDate(String value) {
        String[] strs = value.split(" ");
        return strs[0];
    }
}
