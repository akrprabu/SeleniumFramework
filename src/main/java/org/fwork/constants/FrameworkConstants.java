package org.fwork.constants;

public final class FrameworkConstants {

    private FrameworkConstants (){

    }

    private static final String CONFIGFILEPATH = System.getProperty("user.dir") + "/src/test/java/resources/config.properties";

    public static String getConfigfilepath() {
        return CONFIGFILEPATH;
    }
}
