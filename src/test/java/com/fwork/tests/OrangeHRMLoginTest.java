package com.fwork.tests;


import com.fwork.driver.DriverManager;
import com.fwork.pages.OrangeHRMHomePage;
import com.fwork.pages.OrangeHRMLoginPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public final class OrangeHRMLoginTest extends BaseTest {

    private OrangeHRMLoginTest() {

    }

    @Test
    public void loginLogoutTest() throws InterruptedException {

        String title = new OrangeHRMLoginPage()
                .enterUsename("Admin")
                .enterPassword("admin123")
                .clickSubmit()
                .clickLinkPaul()
                .clickLinkLogout()
                .getTitle();

        Assertions.assertThat(title)
                .isEqualTo("OrangeHRM");



    }
}
