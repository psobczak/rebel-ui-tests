package pl.sobczak.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Screenshot {

    public static void takeScreenshot(WebDriver driver, String fileName) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        String zonedDateTime = ZonedDateTime
                .now(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("uuuu.MM.dd.HH.mm.ss"));
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File outFile = new File("target/screenshots/" + fileName + zonedDateTime + ".jpg");
        try {
            FileUtils.copyFile(srcFile, outFile);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
