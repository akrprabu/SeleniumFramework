package com.fwork.tests;

import com.fwork.driver.DriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public final class LoginPageTest extends BaseTest {

    private LoginPageTest() {

    }


    @Test
    @Description("Google Test")
    @Epic("EP0001")
    @Feature("Test")
    @Story("Story0001")
    public void test1() {
        //System.setProperty()

        DriverManager.getDriver().get("https://www.google.com");
        List<WebElement> links =  DriverManager.getDriver().findElements(By.tagName("a"));
        links.forEach(link -> System.out.println(link.getText()));
        long count = links.stream().filter(link -> !link.getAttribute("href").isEmpty()).count();
        System.out.println(count);
        DriverManager.getDriver().findElement(By.name("q")).sendKeys("Automation", Keys.ENTER);
        Assert.assertTrue(false);

    }

    @Test
    public void test2() {
        //System.setProperty()
        DriverManager.getDriver().get("https://www.google.com");
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
        DriverManager.getDriver().get("https://www.amazon.com");

        Set<String> winids = DriverManager.getDriver().getWindowHandles();
        winids.forEach(winid -> System.out.println(DriverManager.getDriver().switchTo().window(winid).getTitle()));
        //DriverManager.getDriver().findElement(By.name("q")).sendKeys("Testing", Keys.ENTER);

    }
}
