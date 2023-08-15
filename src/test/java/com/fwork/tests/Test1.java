package com.fwork.tests;

import com.fwork.driver.Driver;
import com.fwork.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test1 extends BaseTest{

    private final String baseURL = "https://www.google.com/";

    private final By editSearch = By.name("q");
    private final By buttonSearch = By.name("btnK");

    @Test
    public void googleSearch() {
        DriverManager.getDriver().get(baseURL);
        element(editSearch).sendKeys("     ", Keys.ENTER);
        //element(buttonSearch).click();
        String title = DriverManager.getDriver().getTitle();
        //DriverManager.getDriver().navigate().refresh();
        String text = element(editSearch).getText();
        System.out.println("Text = " + text);
        Assert.assertTrue(text.contains("  "));
        System.out.println();

    }

    public WebElement element(By by) {
               return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(by));

    }


}
