package com.fwork.Report;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class ExtentReport {

    private ExtentReport(){}

//    public static ExtentReports extent;

//    public static void initReport() {
//        if(Objects.isNull(extent)) {
//            extent = new ExtentReports();
//            ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
//            extent.attachReporter(spark);
//            spark.config().setTheme(Theme.STANDARD);
//            spark.config().setDocumentTitle("Selenium Report");
//            spark.config().setReportName("Test");
//        }
//    }
//
//    public static void flushReport() throws IOException {
//        if(Objects.nonNull(extent)) {
//            extent.flush();
//            Desktop.getDesktop().browse(new File("index.html").toURI());
//        }
//    }
//
//    public static void createTest(String testCaseName) {
//        extent.createTest(testCaseName);
//    }
}
