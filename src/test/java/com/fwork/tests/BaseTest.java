package com.fwork.tests;

import com.fwork.driver.Driver;
import com.google.common.util.concurrent.Uninterruptibles;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected BaseTest() {

    }

    @BeforeTest
    protected void beforeTest(ITestContext context) {
        context.setAttribute("classname", this.getClass().getSimpleName()); //This is to use in data provider
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
