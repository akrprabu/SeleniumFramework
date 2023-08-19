package com.fwork.pages;

import com.fwork.Report.AllureReport;
import com.fwork.enums.WaitStrategy;
import org.openqa.selenium.By;

public final class OHRMLoginPage extends BasePage{

    public final By textboxUsername = By.name("username");
    public final By textboxPassword = By.xpath("//input[@name='password' and @type='password']");
    public final By buttonSubmit = By.xpath("//button[@type='submit']");


    public OHRMLoginPage enterUsename(String username) {
        sendKeys(textboxUsername, username, WaitStrategy.PRESENCE);
        AllureReport.saveTextLog("Enter user name on the field " + textboxUsername);
       return this;
    }

    public OHRMLoginPage enterPassword(String password) {
        sendKeys(textboxPassword, password, WaitStrategy.PRESENCE);
        AllureReport.saveTextLog("Enter password on the field " + textboxPassword);
        return this;
    }
    public String getTitle() {
        return getPageTitle();
    }
    public OHRMHomePage clickSubmit() {
        click(buttonSubmit, WaitStrategy.CLICKABLE);
        AllureReport.saveTextLog("Click on submit button " + buttonSubmit);
        return new OHRMHomePage();
    }






}
