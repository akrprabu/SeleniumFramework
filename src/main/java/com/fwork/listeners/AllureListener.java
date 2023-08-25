package com.fwork.listeners;

import com.fwork.Report.AllureReport;
import com.fwork.driver.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;


public class AllureListener implements ITestListener, ISuiteListener {

    public static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    @Attachment
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment
    public byte[] saveFailureScreenshot(WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onStart(ISuite suite) {
        ISuiteListener.super.onStart(suite);
    }

    @Override
    public void onFinish(ISuite suite) {
        ISuiteListener.super.onFinish(suite);


    }

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am on test success method " + getTestMethodName(iTestResult));
        AllureReport.saveTextLog(getTestMethodName(iTestResult) + " passed!");
        Object testClass = iTestResult.getInstance();
        WebDriver driver = DriverManager.getDriver();

        if(driver instanceof WebDriver) {
            System.out.println("Screen shot captured for the test case " + getTestMethodName(iTestResult));
            saveScreenshot(driver);
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am on finish method " + getTestMethodName(iTestResult));

        Object testClass = iTestResult.getInstance();
        WebDriver driver = DriverManager.getDriver();

        if(driver instanceof WebDriver) {
            System.out.println("Screen shot captured for the test case " + getTestMethodName(iTestResult));
            saveFailureScreenshot(driver);
        }
        AllureReport.saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
       System.out.println("I am on the test method " + iTestContext.getName());
       iTestContext.setAttribute("WebDriver", DriverManager.getDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am on finish method " + iTestContext.getName());
    }
}
