package com.fwork.tests;

import com.fwork.driver.Driver;
import com.fwork.driver.DriverManager;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test1 extends BaseTest{

    private final String baseURL = "https://www.google.com/";

    private final By editSearch = By.name("q");
    private final By buttonSearch = By.name("btnK");

    private final String autoZoneUrl = "https://www.autozone.com/";
    private final By searchTextbox = By.xpath("//*[@id=\"searchBoxCompContainer\"]/div/div/div[1]/div/input");
    private final By searchValue = By.xpath("//div[@data-testid='search-suggestion-container']//span[text()='fuel pump']");

    private final By priceCheckbox = By.xpath("//li[@data-testid=\"$10 - $15\"]//input");
    private final By drpdwnMultipleLetCode = By.xpath("//*[@id='superheros']");

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
            try {
//                WebElement we = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
//                        .until(ExpectedConditions.presenceOfElementLocated(by));
                Wait<WebDriver> wait = new FluentWait<>(DriverManager.getDriver())
                        .withTimeout(Duration.ofSeconds(10))
                        .ignoring(NoSuchElementException.class)
                        .ignoring(StaleElementReferenceException.class);
                WebElement we1 = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
//                Actions action = new Actions(DriverManager.getDriver());
//                action.moveToElement(we1).perform();
                we1.sendKeys(Keys.DOWN);
                Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
//                ((JavascriptExecutor)DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true)", we1);
//                Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
                return we1;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

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

    private final By dom = By.xpath("//*[@class='card-footer']/a[text()='DOM']");
    private final By txtbxFullName = By.id("getMe");
    @Test
    public void letCodeDropDowns() {
        DriverManager.getDriver().get("https://letcode.in/dropdowns");
        System.out.println("Multiple drop down------------");
        Select select = new Select(getElement(drpdwnMultipleLetCode));

            List<WebElement> list = select.getOptions();
            list.forEach(m-> System.out.println(m.getText()));

//            select.selectByValue("Aquaman");
            select.selectByIndex(4);
            select.selectByVisibleText("Doctor Strange");
            WebElement sh = select.getFirstSelectedOption();
            System.out.println("First selected option -> " + sh.getText());
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(20));
    }

    @Test
    public void letCodeTextbox() {

        DriverManager.getDriver().get("https://letcode.in/edit");
//        getElement(txtbxFullName).sendKeys("Test");
        String b = getElement(txtbxFullName).getAttribute("value");
        System.out.println(getElement(txtbxFullName).getText());

    }
    @AfterTest
    public void quit() {
        DriverManager.getDriver().quit();
    }

}
