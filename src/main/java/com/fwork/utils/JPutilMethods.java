package com.fwork.utils;

import com.fwork.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class JPutilMethods {

    public static boolean clickDropdown(By dropdown, String searchText) {

        Boolean flag = false;
        try {
            List<WebElement> listElements = DriverManager.getDriver().findElements(dropdown);
            Actions builder = new Actions(DriverManager.getDriver());

            if (listElements != null && listElements.size() >0) {
                for(WebElement we:listElements) {
                    if(we.getText() !=null && we.getText().equalsIgnoreCase(searchText)) {
                        //scrollToElement(we);
                        builder.moveToElement(we).build().perform();
                        //delayFor(2);
                        we.click();
                        flag = true;
                        break;
                    }
                }
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            flag = false;
        }
        //pageMethods().WaitForElementInvisibility(spinnerContainer, spinnerDelay);
        return flag;
    }
}
