package com.fwork.factories;

import com.fwork.driver.DriverManager;
import com.fwork.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWaitFactory {

    public static WebElement performExplicitWait(WaitStrategy waitStrategy, By by) {
        WebElement element = null;
        if (waitStrategy == WaitStrategy.CLICKABLE) {
            element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(by));
        } else if (waitStrategy == WaitStrategy.PRESENCE) {
            element=new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        } else if (waitStrategy == WaitStrategy.VISIBLE) {
            element=new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
        } else if (waitStrategy == WaitStrategy.NONE) {
            element = DriverManager.getDriver().findElement(by);
        //Handle stale element reference error using lambda explicit wait
        } else if (waitStrategy == WaitStrategy.HANDLESTALEELEMENT) {
            element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
                    .until(d -> {
                        System.out.println("Searching for the element");
                        d.navigate().refresh();
                        return d.findElement(by);
                    });
    }


    return element;

}
}
