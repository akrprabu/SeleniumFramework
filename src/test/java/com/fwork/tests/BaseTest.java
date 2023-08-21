package com.fwork.tests;

import com.fwork.driver.Driver;
import com.google.common.util.concurrent.Uninterruptibles;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected BaseTest() {

    }

    @BeforeTest
    protected void beforeTest(ITestContext context) {
        context.setAttribute("classname", this.getClass().getSimpleName()); //This is to use in data provider

        //This is to start docker hub and browser containers
        String[] command = {"cd C:\\Users\\akrpr\\Docker Workspace", "docker-compose up -d"};
        ProcessBuilder builder = new ProcessBuilder(command);
        builder = builder.directory(new File("C:\\Users\\akrpr\\Docker Workspace"));
        try {
            Process p = builder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Uninterruptibles.sleepUninterruptibly(30, TimeUnit.SECONDS);
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
        Driver.quitDriver();

    }

}
