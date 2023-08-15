package com.fwork.pages;

import com.fwork.Report.AllureReport;
import com.fwork.enums.WaitStrategy;
import com.fwork.utils.DynamicPathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class OrangeHRMLeavePage extends BasePage {

    private final By buttonApproveLeave = By.xpath("(//div[@class='orangehrm-container']//div[@role='cell' and normalize-space()='Aaliyah Haq'])[1]/parent::div//button[normalize-space()='Approve']");

    private final By textLeave = By.xpath("//h6");
    private final By textSuccess = By.xpath("//p[normalize-space()='Successfully Updated']");

    private final By subUnitDropDown = By.xpath("//label[text()='Sub Unit']/parent::div/following-sibling::div//*[contains(@class, 'arrow')]");

    public String getLeaveText() {
        return getText(textLeave, WaitStrategy.PRESENCE);
    }
    public OrangeHRMLeavePage clickButtonApproveLeave() {
        click(buttonApproveLeave, WaitStrategy.CLICKABLE);
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        AllureReport.saveTextLog("Click on Approve Leave button");
        return new OrangeHRMLeavePage();

    }


    private String xpathBefore = "(//div[@class='orangehrm-container']//div[@role='cell' and normalize-space()='%replacable%'])[1]/parent::div//button[normalize-space()='Approve']";
    public OrangeHRMLeavePage clickButtonApproveLeaveBasedOnText(String name) {
        String newXpath = DynamicPathUtils.getXpath(xpathBefore,name);
        click(By.xpath(newXpath), WaitStrategy.PRESENCE);
        AllureReport.saveTextLog("Click on Approve Leave for " + name);
        return new OrangeHRMLeavePage();
    }

    public OrangeHRMLeavePage clickSubUnitDrpoDown() {
        click(subUnitDropDown, WaitStrategy.CLICKABLE);
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        return new OrangeHRMLeavePage();

    }

    private String subUnitText = "//label[text()='Sub Unit']/parent::div/following-sibling::div//span[text()='%replacable%']";
    public OrangeHRMLeavePage clickSubUnitText(String text) throws InterruptedException {
        String newXpath = DynamicPathUtils.getXpath(subUnitText,text);
        click(By.xpath(newXpath), WaitStrategy.PRESENCE);
        AllureReport.saveTextLog("Selected " + text + "from Sub Unit drop down" + subUnitText);
        Thread.sleep(4000);
        return new OrangeHRMLeavePage();
    }

    public OrangeHRMLeavePage selectValueFromSubUnit(String visibleText) throws InterruptedException {
        selectAValueFromDropdown(subUnitDropDown, WaitStrategy.PRESENCE, visibleText);
        return new OrangeHRMLeavePage();
    }

    public boolean checkTextSuccessExists() {
        return checkElementExists(textSuccess, WaitStrategy.PRESENCE);

    }
}
