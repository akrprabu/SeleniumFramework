package com.fwork.tests;

import com.fwork.driver.Driver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.Objects;

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
            Driver.initdriver(System.getProperty("browserName").toLowerCase());
        }


    }

    @AfterMethod
    protected void tearDown() {
        Driver.quitDriver();

    }

}
