package com.fwork.pages;

import com.fwork.driver.DriverManager;
import com.fwork.enums.WaitStrategy;
import com.fwork.factories.ExplicitWaitFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {

    protected void click(By by, WaitStrategy waitStrategy) {
        WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
        element.click();
    }

    protected void sendKeys(By by, String value, WaitStrategy waitStrategy) {
        WebElement element =  ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
        element.sendKeys(value);
    }

    protected String getPageTitle() {
        return DriverManager.getDriver().getTitle();
    }

    protected String getText(By by, WaitStrategy waitStrategy) {
        WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
        return element.getText();
    }

    protected boolean checkElementExists(By by, WaitStrategy waitStrategy) {
       ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
        if(DriverManager.getDriver().findElements(by).size() > 0 )
            return true;
        return false;

    }

    protected void selectAValueFromDropdown(By by, WaitStrategy waitStrategy, String selectText) {
        WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
        Select select = new Select(element);
        select.selectByVisibleText(selectText);
    }
}
