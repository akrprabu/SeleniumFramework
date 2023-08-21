package com.fwork.tests;


import com.fwork.pages.OHRMHomePage;
import com.fwork.pages.OHRMLeaveEntitlementPage;
import com.fwork.pages.OHRMLeavePage;
import com.fwork.pages.OHRMLoginPage;
import com.fwork.utils.DataUtils;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;


public final class OHRMLeaveTest extends BaseTest {

    @Test(dataProvider = "dp1")
    public void oraangeHRMLeaveTest(String name)  {

        System.out.println(name);
    }

    @DataProvider(name="dp1")
    public String[][] getData() {

        String[][] data = new String[][]
                {{"Test1", "Test2"},
                {"Test3", "Test4"}};
        return data;
    }

    @Test
    public void approveLeaveTest() throws InterruptedException {
        String title = new OHRMLoginPage()
                .enterUsename("admin")
                .enterPassword("admin123")
                .clickSubmit()
                .getTitle();

        Assertions.assertThat(title)
                .isEqualTo("OrangeHRM");

        String text = new OHRMHomePage()
                .clickLinkLeave()
                .getLeaveText();

        Assertions.assertThat(text)
                .isEqualTo("Leave");

        boolean flag = new OHRMLeavePage()
                .clickSubUnitDrpoDown()
                .clickSubUnitText("Engineering")
                .clickButtonApproveLeaveBasedOnText("Anthony Nolan")
                .checkTextSuccessExists();

        Assertions.assertThat(flag).isTrue();

    }

    @Test (dataProviderClass = DataUtils.class, dataProvider = "getDataFromJson")
    public void addLeavesForAnEmployee(HashMap<String, String > hashMap) {

        String title = new OHRMLoginPage()
                .enterUsename("admin")
                .enterPassword("admin123")
                .clickSubmit()
                .getTitle();

        Assertions.assertThat(title)
                .isEqualTo("OrangeHRM");

        String text = new OHRMHomePage()
                .clickLinkLeave()
                .getLeaveText();

        Assertions.assertThat(text)
                .isEqualTo("Leave");

        boolean checkAddedTextIfExists = new OHRMLeaveEntitlementPage()
                .clickAddEntitlment()
                .setEmployeeName(hashMap.get("Name"))
                .clickAjaxName()
                .selectLeaveType(hashMap.get("Leave Type"))
                .setEntitlement(hashMap.get("Entitlement"))
                .clickOnSave()
                .clickConfirm()
                .checkAddedTextExists();

        Assert.assertTrue(checkAddedTextIfExists);
    }
}
