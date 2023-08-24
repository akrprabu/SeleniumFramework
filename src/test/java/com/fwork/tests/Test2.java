package com.fwork.tests;

import com.fwork.driver.DriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test2 extends BaseTest{

 private static final String url = "https://tutorialsninja.com/demo/";

 private static final int waitTime = 30;
 private static final By searchBox = By.name("search");
 private static final By productThumbs = By.xpath("//div[@class='product-thumb']");

 @Test
 public void searchTest() {
     DriverManager.getDriver().get(url);
     getElement(searchBox).sendKeys("iphone", Keys.ENTER);
     boolean elementIsDisplayed = getElement(productThumbs).isDisplayed();
             assertThat(elementIsDisplayed)
                     .isTrue();

 }

 public static WebElement getElement(By by) {
     return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(waitTime))
             .until(ExpectedConditions.presenceOfElementLocated(by));
 }


}
