package com.fwork.tests;


import com.fwork.pages.OHRMLoginPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public final class OrangeHRMLoginTest extends BaseTest {

    private OrangeHRMLoginTest() {

    }

    @Test(dataProvider = "LoginTestDataProvider")
    public void loginLogoutTest(String username, String password)  {

        String title = new OHRMLoginPage()
                .enterUsename(username)
                .enterPassword(password)
                .clickSubmit()
                .clickLinkPaul()
                .clickLinkLogout()
                .getTitle();

        Assertions.assertThat(title)
                .isEqualTo("OrangeHRM");

    }

    @DataProvider(name="LoginTestDataProvider", parallel = true)
    public Object[][] getData() {

        return new Object[][] {
                {"Admin", "admin123"},
                {"Admin123", "admin1234"}
        };

    }
}
