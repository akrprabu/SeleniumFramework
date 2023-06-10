package com.fwork.pages;

import com.fwork.enums.WaitStrategy;
import org.openqa.selenium.By;

public final class OrangeHRMLoginPage extends BasePage{

    public final By textboxUsername = By.name("username");
    public final By textboxPassword = By.xpath("//input[@name='password' and @type='password']");
    public final By buttonSubmit = By.xpath("//button[@type='submit']");

    public OrangeHRMLoginPage enterUsename(String username) {
        sendKeys(textboxUsername, username, WaitStrategy.PRESENCE);
       return this;
    }

    public OrangeHRMLoginPage enterPassword(String password) {
        sendKeys(textboxPassword, password, WaitStrategy.PRESENCE);
        return this;
    }

    public OrangeHRMHomePage clickSubmit() {
        click(buttonSubmit, WaitStrategy.CLICKABLE);
        return new OrangeHRMHomePage();
    }

    public String getTitle() {
        return getPageTitle();
    }




}
