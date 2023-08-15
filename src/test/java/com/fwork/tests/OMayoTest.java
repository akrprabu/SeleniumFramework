package com.fwork.tests;

import com.fwork.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class OMayoTest extends BaseTest {

    private final String baseURL = "https://omayo.blogspot.com";
    private final int waitTime=10;
    private final By userNameField = By.xpath("//form[@name='form1']//input[@type='text']");
    private final By password = By.xpath("//form[@name='form1']//input[@type='password']");

    private final By loginButton = By.xpath("//button[normalize-space()='LogIn']");

    private final By selectDropDown = By.xpath("//select[@id='drop1']");


    @Test
    public void omayoTest() {
        DriverManager.getDriver().get(baseURL);
        getElement(userNameField).sendKeys("Test1");
        getElement(password).sendKeys("Test");


        Assert.assertTrue(getElement(loginButton).isDisplayed());
        Assert.assertTrue(checkValueExistsInADropDown("Doc 1", selectDropDown));


    }

    public WebElement getElement(By by) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(waitTime))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public boolean checkValueExistsInADropDown(String value, By by) {

        Select select = new Select(getElement(by));

        List<WebElement> listElements = select.getOptions();

        return listElements.stream().anyMatch(element->element.getText().equalsIgnoreCase(value));


    }


}
