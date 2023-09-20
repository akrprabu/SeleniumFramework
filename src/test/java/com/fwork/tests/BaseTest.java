package com.fwork.tests;

import com.fwork.driver.Driver;
import com.fwork.enums.ConfigProperties;
import com.fwork.utils.ReadPropertyFileUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected BaseTest() {

    }
    public static String runmode = null;
    @BeforeTest
    protected void beforeTest(ITestContext context) {
        context.setAttribute("classname", this.getClass().getSimpleName()); //This is to use in data provider

        try {
            runmode = ReadPropertyFileUtils.getValue(ConfigProperties.RUNMODE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //This is to start docker hub and browser containers
        if(runmode.equalsIgnoreCase("remote")) {
            try {
                Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd C:\\Users\\akrpr\\Docker Workspace && docker-compose up -d --scale chrome=4 --scale firefox=4\"");
                //Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd C:\\Users\\akrpr\\Docker Workspace && docker-compose up -d docker-compose.yml up --scale chrome=4\"");
                Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(10));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Uninterruptibles.sleepUninterruptibly(15, TimeUnit.SECONDS);
    }
    @Parameters({"browser"})
    @BeforeMethod
    protected void setUp(@Optional("chrome") String browserName) throws Exception {

        if (Objects.isNull(System.getProperty("browser"))) {
            Driver.initdriver(browserName.toLowerCase());
        } else {

            Driver.initdriver(System.getProperty("browser").toLowerCase());
        }


    }

    @AfterMethod
    protected void tearDown() {
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        //Driver.quitDriver();


    }
    @AfterTest
    protected void afterClass() {

        if(runmode.equalsIgnoreCase("remote")) {
            try {
                Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd C:\\Users\\akrpr\\Docker Workspace && docker-compose down\"");
                //Runtime.getRuntime().exec("cmd /c cmd.exe /K \"cd C:\\Users\\akrpr\\IdeaProjects\\SeleniumFramework && allure serve\"");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
