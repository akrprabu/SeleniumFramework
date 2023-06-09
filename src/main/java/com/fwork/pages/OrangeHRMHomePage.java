package com.fwork.pages;

import com.fwork.driver.DriverManager;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

// Assertions should not be in Page layers, All page layers should have a return type
public class OrangeHRMHomePage {

    private final By link_paul = By.xpath("//*[text()[contains(.,'Paul')]]");
    private final By link_logout = By.xpath("//a[text()[contains(.,'Logout')]]");

    public OrangeHRMHomePage clickLinkPaul() {
        DriverManager.getDriver().findElement(link_paul).click();
        return this;

    }

    public OrangeHRMLoginPage clickLinkLogout() throws InterruptedException {
        DriverManager.getDriver().findElement(link_logout).click();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        return new OrangeHRMLoginPage();

    }
}
