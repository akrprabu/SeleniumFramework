package com.fwork.tests;


import com.fwork.driver.DriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public final class HomePageTest extends BaseTest {

    private HomePageTest() {

    }

    @Test
    public void test3() {
        DriverManager.getDriver().findElement(By.name("q")).sendKeys("testing mini bytes", Keys.ENTER);
        String title=DriverManager.getDriver().getTitle();
        //Assertion using testNG
        Assert.assertTrue(Objects.nonNull(title));
        Assert.assertTrue(title.contains("Google Search"));
        Assert.assertTrue(title.length() > 15);
        Assert.assertTrue(title.length() < 100);
        Assert.assertTrue(title.toLowerCase().matches("\\w.*" + "google search")); //\\w. means [a-zA-Z0-9]

        List< WebElement> elements =DriverManager.getDriver().findElements(By.xpath("//h3/span"));
        Assert.assertEquals(elements.size(), 1);

        //Assertion using assertJ
        Assertions.assertThat(title)
                .isNotNull()
                .containsIgnoringCase("google search")
                .matches("\\w.*" + "google search")
                .hasSizeBetween(15, 100);

        Assertions.assertThat(elements)
                .hasSize(1)
                .extracting(e -> e.getText())
                .contains("testing mini");

    }
}
