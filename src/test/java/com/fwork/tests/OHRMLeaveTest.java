package com.fwork.tests;


import com.fwork.pages.OrangeHRMHomePage;
import com.fwork.pages.OrangeHRMLeaveEntitlementPage;
import com.fwork.pages.OrangeHRMLeavePage;
import com.fwork.pages.OrangeHRMLoginPage;
import com.fwork.utils.DataUtils;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

public final class OHRMLeaveTest extends BaseTest {

    @Test
    public void oraangeHRMLeaveTest() throws IOException {




    }

    @Test
    public void approveLeaveTest() throws InterruptedException {
        String title = new OrangeHRMLoginPage()
                .enterUsename("admin")
                .enterPassword("admin123")
                .clickSubmit()
                .getTitle();

        Assertions.assertThat(title)
                .isEqualTo("OrangeHRM");

        String text = new OrangeHRMHomePage()
                .clickLinkLeave()
                .getLeaveText();

        Assertions.assertThat(text)
                .isEqualTo("Leave");

        boolean flag = new OrangeHRMLeavePage()
                .clickSubUnitDrpoDown()
                .clickSubUnitText("Engineering")
                .clickButtonApproveLeaveBasedOnText("Anthony Nolan")
                .checkTextSuccessExists();

        Assertions.assertThat(flag);
    }

    @Test (dataProviderClass = DataUtils.class, dataProvider = "getDataFromJson")
    public void addLeavesForAnEmployee(HashMap<String, String > hashMap) {

        String title = new OrangeHRMLoginPage()
                .enterUsename("admin")
                .enterPassword("admin123")
                .clickSubmit()
                .getTitle();

        Assertions.assertThat(title)
                .isEqualTo("OrangeHRM");

        String text = new OrangeHRMHomePage()
                .clickLinkLeave()
                .getLeaveText();

        Assertions.assertThat(text)
                .isEqualTo("Leave");

        boolean checkAddedTextIfExists = new OrangeHRMLeaveEntitlementPage()
                .clickAddEntitlment()
                .setEmployeeName(hashMap.get("Name"))
                .clickAjaxName()
                .selectLeaveType(hashMap.get("Leave Type"))
                .setEntitlement("Entitlement")
                .clickOnSave()
                .clickConfirm()
                .checkAddedTextExists();

        Assert.assertTrue(checkAddedTextIfExists);
    }
}
