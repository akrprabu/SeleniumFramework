package com.fwork.utils;

import com.fwork.enums.ConfigProperties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
/**
 * Jan 26, 2023
 * @author Prabu Komaravel
 * @version 1.0
 */
public final class ReadPropertyFileUtils {

    private ReadPropertyFileUtils() {}

    private static final Properties property = new Properties();
    private static String env = System.getProperty("env");


    static {

        if(env == null) {
            env = "qa";
        }
        String file = env.toLowerCase() +".Config.properties";
       try {

           //FileInputStream file = new FileInputStream(FrameworkConstants.getConfigfilepath() + env.toLowerCase() +".Config.properties");
           /** It is not a good practice to load file as above. When we compile the entire project into a jar file then it will not find the file
            * Hence, added the below code. Since this is a static block, added as ReadPropertyFile.class.getClassLoader()
            * If it is not a static block then, we can add like getClass().getClassLoader()....
            */

           InputStream inputStream = ReadPropertyFileUtils.class.getClassLoader().getResourceAsStream(file);

            property.load(inputStream);

        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public static String getValue(ConfigProperties key)  {

        if(Objects.isNull(property.getProperty(String.valueOf(key))) || Objects.isNull(key)){ //or value == null
            throw new RuntimeException("Property name " + key + " is not found. Please check config.properties file");
        }
        return property.getProperty(String.valueOf(key));
    }
}
