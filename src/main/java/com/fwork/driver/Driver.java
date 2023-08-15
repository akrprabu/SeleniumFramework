package com.fwork.driver;

import com.fwork.factories.BrowserFactory;
import com.fwork.utils.ReadPropertyFile;
import java.net.MalformedURLException;
import java.util.Objects;

public final class Driver {

    private Driver() {

    }

    public static void initdriver(String browser) throws Exception {

        //This code added before setting up selenium grid
//        if (Objects.isNull(DriverManager.getDriver())) {
//            WebDriver driver = new ChromeDriver();
//            DriverManager.setDriver(BrowserFactory.getBrowserDriver(browser));
//            DriverManager.getDriver().get(ReadPropertyFile.getValue("url"));
//            DriverManager.getDriver().manage().window().maximize();
//        }

        if(Objects.isNull(DriverManager.getDriver())) {
            try {
                DriverManager.setDriver(BrowserFactory.getBrowserDriver(browser));

            } catch (MalformedURLException e) {
                throw new RuntimeException("Please check the capabilities of browser");
            }
            //DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.URL));

            DriverManager.getDriver().get(ReadPropertyFile.getValue("url"));
            //DriverManager.getDriver().manage().window().maximize();
        }

    }

    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }

    }

}
