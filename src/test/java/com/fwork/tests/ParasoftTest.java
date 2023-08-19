package com.fwork.tests;

import com.fwork.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ParasoftTest extends BaseTest{

    private final int waitTime = 10;
    private final String url = "https://parabank.parasoft.com/parabank/index.htm";
    private final String url2 = "https://omayo.blogspot.com/";

    private final By adminPageMenu =By.xpath("//a[text()='Admin Page']");
    private final By selectDrop = By.id("loanProvider");

//    @BeforeClass
//    public void beforeClass(){
//        DriverManager.getDriver().get(url);
//    }
//
//    @AfterClass
//    public void afterClass() {
//        DriverManager.getDriver().quit();
//    }
    @Test
    public void paraSoftTest()  {


        final By accountTable = By.id("accountTable");
        final By userName = By.name("username");
        final By password = By.name("password");
        final By submit = By.xpath("//*[@type='submit']");


        DriverManager.getDriver().get(url);
//        if (!elementExists(accountTable)) {
//            element(adminPageMenu).click();
//
//        }

        element(adminPageMenu).click();
        boolean isPageReady = ((JavascriptExecutor)DriverManager.getDriver()).executeScript("return document.readyState").equals("complete");
        Assert.assertTrue(isPageReady);

        JavascriptExecutor jse = (JavascriptExecutor)DriverManager.getDriver();
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        selectDropDownValue("Local");

    }
    @Test
    public void getDropdownValueTest() {
        final By dropDown = By.name("SiteMap");
        DriverManager.getDriver().get(url2);

        Select select = new Select(element(dropDown));
        List<WebElement> options = select.getOptions();
        boolean valueExist = false;
//        for(int i=0; i<options.size(); i++) {
//            if(options.get(i).getText().equals("doc 2")) {
//                valueExist=true;
//                break;
//            }
//        }

        for(WebElement w:options) {
            if (w.getText().equals("doc 2")) {
                valueExist = true;
                break;
            }
        }
         boolean result = options.stream().anyMatch(m-> m.getText().equals("doc 21"));
         Assert.assertTrue(result);
    }

    public WebElement element(By elementLocator) {

        WebElement ele = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(waitTime))
                .until(ExpectedConditions.presenceOfElementLocated(elementLocator));

        JavascriptExecutor jse = (JavascriptExecutor)DriverManager.getDriver();
        jse.executeScript("arguments[0].style.background='yellow'", ele);
        return ele;
    }

    public boolean elementExists(By elementLocator) {

        try {
           DriverManager.getDriver().findElement(elementLocator);
        } catch(NoSuchElementException e) {
            return false;

        }
        return true;
    }

    public void selectDropDownValue(String value) {
        WebElement element = DriverManager.getDriver().findElement(selectDrop);
        Select select = new Select(element);
        List<WebElement> listValues = select.getOptions();
        for(WebElement w:listValues) {
            if(w.getText().equalsIgnoreCase(value)) {
                w.click();
                break;
            } else {
                throw new RuntimeException(value + " does not exists in the drop down, please check");
            }
        }
    }
}
