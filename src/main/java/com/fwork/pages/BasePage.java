package com.fwork.pages;

import com.fwork.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected void click(By by) {
        explicitlyWaitElementToBeClickable(by);
        DriverManager.getDriver().findElement(by).click();
    }

    protected void sendKeys(By by, String value) {
        explicitlyWaitElementToBeClickable(by);
        DriverManager.getDriver().findElement(by).sendKeys(value);
    }

    protected String getPageTitle() {
        return DriverManager.getDriver().getTitle();
    }

    private void explicitlyWaitElementToBeClickable(By by) {
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(by));
    }
}
