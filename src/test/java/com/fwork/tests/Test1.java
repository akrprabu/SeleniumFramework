package com.fwork.tests;

import com.fwork.driver.Driver;
import com.fwork.driver.DriverManager;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    private final String autoZoneUrl = "https://www.autozone.com/";
    private final By searchTextbox = By.xpath("//input[@role='combobox']");
    private final By searchValue = By.xpath("//div[@data-testid='search-suggestion-container']//span[text()='fuel pump']");

    private final By priceCheckbox = By.xpath("//li[@data-testid=\"$10 - $15\"]//input");

    @Test
    public void googleSearch() {
        DriverManager.getDriver().get(baseURL);
        getElement(editSearch).sendKeys("     ", Keys.ENTER);
        //element(buttonSearch).click();
        String title = DriverManager.getDriver().getTitle();
        //DriverManager.getDriver().navigate().refresh();
        String text = getElement(editSearch).getText();
        System.out.println("Text = " + text);
        Assert.assertTrue(text.contains("  "));
        System.out.println();

    }

    public WebElement getElement(By by) {
               return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(by));

    }

    @Test
    public void searchFuelPump() {

        DriverManager.getDriver().get(autoZoneUrl);
        getElement(searchTextbox).click();
        getElement(searchValue).click();

        String value = getElement(priceCheckbox).getAttribute("checked");
        System.out.println(value);
        getElement(priceCheckbox).click();
        System.out.println("---------");
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        String value1 = getElement(priceCheckbox).getAttribute("checked");
        System.out.println(value1);
        System.out.println("---------");
        System.out.println(getElement(priceCheckbox).isSelected());

        ((JavascriptExecutor)DriverManager.getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");

    }


}
