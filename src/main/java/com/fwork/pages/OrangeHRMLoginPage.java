package com.fwork.pages;

import com.fwork.driver.DriverManager;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public final class OrangeHRMLoginPage {

    public final By textbox_username = By.name("username");
    public final By textbox_password = By.xpath("//input[@name='password' and @type='password']");
    public final By button_submit = By.xpath("//button[@type='submit']");

    public OrangeHRMLoginPage enterUsename(String username) throws InterruptedException {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        DriverManager.getDriver().findElement(textbox_username).sendKeys(username);
        Thread.sleep(5000);
        return this;
    }

    public OrangeHRMLoginPage enterPassword(String password) {
        DriverManager.getDriver().findElement(textbox_password).sendKeys(password);
        return this;
    }

    public OrangeHRMHomePage clickSubmit() {
        DriverManager.getDriver().findElement(button_submit).click();
        return new OrangeHRMHomePage();
    }

    public String getTitle() {
        return DriverManager.getDriver().getTitle();
    }


}
