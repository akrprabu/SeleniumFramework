package com.fwork.tests;

import com.fwork.driver.DriverManager;
import com.google.common.util.concurrent.Uninterruptibles;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class OMayoTest extends BaseTest {

    private final String baseURL = "https://omayo.blogspot.com";
    private final int waitTime=10;
    private final By userNameField = By.xpath("//form[@name='form1']//input[@type='text']");
    private final By password = By.xpath("//form[@name='form1']//input[@type='password']");

    private final By loginButton = By.xpath("//button[normalize-space()='LogIn']");

    private final By selectDropDown = By.xpath("//select[@id='drop1']");

    private final By links = By.tagName("a");


    @Test(enabled = false)
    public void omayoTest() {
        DriverManager.getDriver().get(baseURL);
        getElement(userNameField).sendKeys("Test1");
        getElement(password).sendKeys("Test");


        Assert.assertTrue(getElement(loginButton).isDisplayed());
        Assert.assertTrue(checkValueExistsInADropDown("Doc 1", selectDropDown));

        removeBlankLinks(links);
    }

    /**
     * This test is to check all the check box for all the male gender in the table
     */
    @Test
    public void s3TableVerification() {
        DriverManager.getDriver().get("https://vins-udemy.s3.amazonaws.com/java/html/java8-stream-table.html");
        DriverManager.getDriver().findElements(By.tagName("tr"))
                .stream()
                .skip(1)
                .map(tr -> tr.findElements(By.tagName("td")))
                .filter(tdList -> tdList.get(1).getText().equalsIgnoreCase("male"))
                .map(tdList -> tdList.get(3))
                .map(td -> td.findElement(By.tagName("input")))
                .forEach(WebElement::click);
        Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
    }
    /**
     * This test is to select the radio button for the min price row in a table
     */
    @Test
    public void s3TableSelectMinRow() {
        DriverManager.getDriver().get("https://vins-udemy.s3.amazonaws.com/java/html/java8-stream-table-price.html");
        Optional<List<WebElement>> minRow =
                DriverManager.getDriver().findElements(By.xpath("//table[@id='prods']//tr"))
                .stream()
                .skip(1)
                .map(tr -> tr.findElements(By.tagName("td")))
                .min(Comparator.comparing(tdList -> Integer.parseInt(tdList.get(2).getText())));


        if(minRow.isPresent()) {
            List<WebElement> cells = minRow.get();
            cells.get(3).findElement(By.tagName("input")).click();
        }

        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);

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

    public void removeBlankLinks(By by) {

        List<WebElement> elementList = DriverManager.getDriver().findElements(by);

        System.out.println(elementList.size());

       List<WebElement> list = elementList.stream()
               .filter(link -> link.getText().trim().length() !=0)
               .collect(Collectors.toList());

    }

    /**
     * This test is to check if the table sorting order is correct for the column 'age' in a table
     */

    @Test (enabled = false)
    public void checkColumnSort() {
        DriverManager.getDriver().get("https://omayo.blogspot.com/");
        WebElement element = DriverManager.getDriver().findElement(By.xpath("//table[@id='table1']//tr"));
        ((JavascriptExecutor)DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true)", element);
        List<Integer> ageList = DriverManager.getDriver().findElements(By.xpath(("//table[@id='table1']//tr")))
                .stream()
                .skip(1)
                .map(tr -> tr.findElements(By.tagName("td")))
                .map(tdList -> Integer.parseInt(tdList.get(1).getText())).collect(Collectors.toList());
        //Ternary operator at the end is important to collect as a list. Got an error before adding collect


        List<Integer> ageListAfterSort = ageList.stream().sorted().collect(Collectors.toList());

        SoftAssertions softly = new SoftAssertions();

            softly.assertThat(ageListAfterSort)
                    .hasSize(4)
                    .isEqualTo(ageList)
                    .contains(25)
                    .isNotEmpty()
                    .isNotNull();

                softly.assertAll();
    }
}
