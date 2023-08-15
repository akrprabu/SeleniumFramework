package com.fwork.Report;

import io.qameta.allure.Attachment;

public class AllureReport {
    @Attachment(value = "{0}", type="text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

}
