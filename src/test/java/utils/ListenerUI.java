package utils;

import com.google.common.io.BaseEncoding;
import driver.WebDriverSingleton;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ListenerUI implements ITestListener {
    protected Logger log = LogManager.getRootLogger();
    protected static final String SCREEN_PATH = ".//target/screenshots/";
    protected static final String SCREEN_FILE_FORMAT = ".png";

    @Override
    public void onTestStart(ITestResult result) {
        log.info(String.format("test %s started", result.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(String.format("test %s passed successful", result.getName()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("Test failure: {}", result.getName());
        String fileNameAppender = getCurrentTimeAsString();
        try {
            saveScreenshot(fileNameAppender);
            attachScreenshotToAllure(fileNameAppender);
            attachScreenToReportPortal(fileNameAppender);
        } catch (IOException exception) {
            log.info("Failed to attach screenshot: {}", exception.getLocalizedMessage());
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }

    private void saveScreenshot(String fileNameAppender) {
        File screenCapture = ((TakesScreenshot) WebDriverSingleton.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(SCREEN_PATH + fileNameAppender + SCREEN_FILE_FORMAT));
        } catch (IOException e) {
            log.error("Failed to save screenshot: {}", e.getLocalizedMessage());
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] attachScreenshotToAllure(String fileNameAppender) throws IOException {
        File file = new File(SCREEN_PATH+fileNameAppender+SCREEN_FILE_FORMAT);
        return FileUtils.readFileToByteArray(file);
    }

    protected void attachScreenToReportPortal(String fileNameAppender) throws IOException {
        File file = new File(SCREEN_PATH + fileNameAppender + SCREEN_FILE_FORMAT);
        log.info("RP_MESSAGE#BASE64#{}#{}", BaseEncoding.base64().encode(FileUtils.readFileToByteArray(file)), "Test failed, screenshot:");
        log.info("trying to attach screenshot from source: " + SCREEN_PATH + fileNameAppender + SCREEN_FILE_FORMAT);
    }
}
