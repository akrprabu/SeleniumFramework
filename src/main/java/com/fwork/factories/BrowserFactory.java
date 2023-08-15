package com.fwork.factories;

import com.fwork.utils.ReadPropertyFile;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public final class BrowserFactory {

    private BrowserFactory() {}

    /**
     *
     * @author Prabu Komaravel
     * Mar 20, 2023
     * @param browser
     * @return
     * @throws MalformedURLException
     * TODO Remove hardcoded value of grid url
     */

    public static WebDriver getBrowserDriver(String browser) throws MalformedURLException {

        String runmode = null;
        try {
            runmode = ReadPropertyFile.getValue("runmode");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //AbstractDriverOptions options = null;  //This also works
        MutableCapabilities options = null;
        WebDriver driver = null;

        if(browser.equalsIgnoreCase("chrome")) {
            if(runmode.equalsIgnoreCase("remote")) {
                options = new ChromeOptions();
                options.setCapability("browserName", "chrome");
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);

            } else {
                driver = new ChromeDriver();
            }

        } else if (browser.equalsIgnoreCase("firefox")) {
            if(runmode.equalsIgnoreCase("remote")) {
                options = new FirefoxOptions();
                options.setCapability("browserName", "firefox");
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
            } else {
                driver = new FirefoxDriver();
            }
        } else if (browser.equalsIgnoreCase("edge")) {
            if(runmode.equalsIgnoreCase("remote")) {
                options = new EdgeOptions();
                options.setCapability("browserName", "MicrosoftEdge");
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
            } else {
                driver = new EdgeDriver();

            }
        }

        driver.manage().window().maximize();
        return driver;
    }

}
