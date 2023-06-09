package com.fwork.tests;

import com.fwork.driver.Driver;
import com.fwork.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public final class LoginPageTest extends BaseTest {

    private LoginPageTest() {

    }


    @Test
    public void test1() {
        //System.setProperty()


        DriverManager.getDriver().findElement(By.name("q")).sendKeys("Automation", Keys.ENTER);

    }

    @Test
    public void test2() {
        //System.setProperty()

        DriverManager.getDriver().findElement(By.name("q")).sendKeys("Testing", Keys.ENTER);

    }
}
