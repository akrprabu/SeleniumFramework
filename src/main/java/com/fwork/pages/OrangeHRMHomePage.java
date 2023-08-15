package com.fwork.pages;

import com.fwork.enums.WaitStrategy;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

// Assertions should not be in Page layers, All page layers should have a return type
public final class OrangeHRMHomePage extends BasePage{

    private final By linkPaul = By.xpath("//*[text()[contains(.,'User')]]");
    private final By linkLogout = By.xpath("//a[text()[contains(.,'Logout')]]");

    private final By linkLeave = By.xpath((" //nav[@role='navigation']//*[contains(@class, 'na') and normalize-space()='Leave']"));



    public OrangeHRMHomePage clickLinkPaul() {
       click(linkPaul, WaitStrategy.PRESENCE);
        return this;

    }

    public OrangeHRMLoginPage clickLinkLogout() {
        click(linkLogout, WaitStrategy.CLICKABLE);
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        return new OrangeHRMLoginPage();

    }

    public OrangeHRMLeavePage clickLinkLeave() {
        click(linkLeave, WaitStrategy.CLICKABLE);
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        return new OrangeHRMLeavePage();

    }


    public String getTitle() {
        return getPageTitle();
    }
}
