package com.fwork.utils;

public final class DynamicPathUtils {

    private DynamicPathUtils() {}

    public static String getXpath(String xpath, String value) {
        return xpath.replace("%replacable%", value);


    }
}
