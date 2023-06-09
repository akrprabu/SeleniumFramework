package com.fwork.utils;

import org.fwork.constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public final class ReadPropertyFile {

    private ReadPropertyFile() {

    }
    private static Properties property = new Properties();
    static {
        try {
            FileInputStream file = new FileInputStream(FrameworkConstants.getConfigfilepath());

            property.load(file);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) throws Exception {
        String value="";

        if(Objects.isNull(property.getProperty(key)) || Objects.isNull(key)){ //or value == null
            throw new Exception("Property name " + key + " is not found. Please check config.properties file");
        }
        return property.getProperty(key);
    }
}
