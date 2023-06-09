package com.fwork.pages;

import org.openqa.selenium.By;

public final class OrangeHRMLoginPage extends BasePage{

    public final By textboxUsername = By.name("username");
    public final By textboxPassword = By.xpath("//input[@name='password' and @type='password']");
    public final By buttonSubmit = By.xpath("//button[@type='submit']");

    public OrangeHRMLoginPage enterUsename(String username) {
        sendKeys(textboxUsername, username);
       return this;
    }

    public OrangeHRMLoginPage enterPassword(String password) {
        sendKeys(textboxPassword, password);
        return this;
    }

    public OrangeHRMHomePage clickSubmit() {
        click(buttonSubmit);
        return new OrangeHRMHomePage();
    }

    public String getTitle() {
        return getPageTitle();
    }


}
