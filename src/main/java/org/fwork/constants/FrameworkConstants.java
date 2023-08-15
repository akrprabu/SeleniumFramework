package org.fwork.constants;

public final class FrameworkConstants {

    private FrameworkConstants (){

    }

    private static final String CONFIGFILEPATH = System.getProperty("user.dir") + "/src/main/java/resources/";
    private static final int EXPLICITWAIT = 10;
    public static String getConfigfilepath() {
        return CONFIGFILEPATH;
    }

    private static int getExplicitwait () {return EXPLICITWAIT;}


}
