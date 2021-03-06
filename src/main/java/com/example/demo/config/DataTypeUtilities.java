package com.example.demo.config;

import com.mysql.jdbc.StringUtils;

import java.util.regex.Pattern;

public class DataTypeUtilities {

    private final static Pattern UUID_PATTERN =
            Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    public static boolean isUUID(String str) {
        return !StringUtils.isNullOrEmpty(str) && UUID_PATTERN.matcher(str).matches();
    }
}
