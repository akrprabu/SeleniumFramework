package com.fwork.pages;

import com.fwork.Report.AllureReport;
import com.fwork.enums.WaitStrategy;
import com.fwork.utils.DynamicPathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class OHRMLeaveEntitlementPage extends BasePage {


    private final By entitlementMenu = By.xpath("//nav[@aria-label='Topbar Menu']//*[text()='Entitlements ']");

    private final By addEntitlementMenu = By.xpath("//nav[@aria-label='Topbar Menu']//*[text()='Add Entitlements']");

    private final By leaveTypeDropDown = By.xpath("//label[contains(text(),'Leave Type')]/parent::div/following-sibling::div//*[contains(@class, 'arrow')]");

    private final By saveButton = By.xpath("//button[text()=' Save ']");

    private final By nameAjax = By.xpath("//form[@class='oxd-form']//label[contains(text(), 'Employee Name')]/parent::div/following-sibling::div//span");

    private final By cofirmButton = By.xpath("//button[text()=' Confirm ']");

    private final By addedTextCell = By.xpath("(//div[@class='oxd-table-card']//div[@role='cell'])[3]/div");

    public OHRMLeaveEntitlementPage clickAddEntitlment() {
        click(entitlementMenu, WaitStrategy.CLICKABLE);
        click(addEntitlementMenu, WaitStrategy.CLICKABLE);
        AllureReport.saveTextLog("Click Entitlement > Add Entitlement");
        return new OHRMLeaveEntitlementPage();

    }

    private String formInput = "//form[@class='oxd-form']//label[contains(text(), '%replacable%')]/parent::div/following-sibling::div//input";

    public OHRMLeaveEntitlementPage setEmployeeName(String name) {
        String newXpath = DynamicPathUtils.getXpath(formInput,"Employee Name");
        sendKeys(By.xpath(newXpath), name, WaitStrategy.PRESENCE);
        AllureReport.saveTextLog("Set employee name: " + name);
        return new OHRMLeaveEntitlementPage();
    }


    public OHRMLeaveEntitlementPage clickAjaxName() {
        click(nameAjax, WaitStrategy.VISIBLE);
        return new OHRMLeaveEntitlementPage();
    }
    private String leaveTypeText = "//label[contains(text(),'Leave Type')]/parent::div/following-sibling::div//span[text()='%replacable%']";

    public OHRMLeaveEntitlementPage selectLeaveType(String leaveType) {
        click(leaveTypeDropDown, WaitStrategy.PRESENCE);
        String newXpath = DynamicPathUtils.getXpath(leaveTypeText, leaveType);
        click(By.xpath(newXpath), WaitStrategy.VISIBLE);
        AllureReport.saveTextLog("Select Leave Type: " + leaveType);
        return new OHRMLeaveEntitlementPage();

    }

    public OHRMLeaveEntitlementPage setEntitlement(String noOfDays) {
        String newXpath = DynamicPathUtils.getXpath(formInput,"Entitlement");
        sendKeys(By.xpath(newXpath), noOfDays, WaitStrategy.PRESENCE);
        AllureReport.saveTextLog("Set entitlement input: " + noOfDays);
        return new OHRMLeaveEntitlementPage();
    }

    public OHRMLeaveEntitlementPage clickOnSave() {
        click(saveButton, WaitStrategy.CLICKABLE);
        AllureReport.saveTextLog("Save button is clicked");
        return new OHRMLeaveEntitlementPage();
    }

    public OHRMLeaveEntitlementPage clickConfirm() {
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
       click(cofirmButton, WaitStrategy.CLICKABLE);
        return new OHRMLeaveEntitlementPage();

    }

    public boolean checkAddedTextExists() {
        return checkElementExists(addedTextCell, WaitStrategy.VISIBLE);
    }



}
