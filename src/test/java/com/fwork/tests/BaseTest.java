package com.fwork.tests;

import com.fwork.driver.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected BaseTest() {

    }

    @BeforeMethod
    protected void setUp() throws Exception {
        Driver.initdriver();
    }

    @AfterMethod
    protected void tearDown() {
        Driver.quitDriver();

    }

}
