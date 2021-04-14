package com.Common.Base;

import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotManager
{
    private static String FAIL_SCENARIO_PATH = "target/screenshots/failure/%s.png";

    public static void saveScreenshot(WebDriver driver, String path)
    {
        if (path == null)
        {
            throw new IllegalArgumentException("Screenshot file name must not be null.");
        }

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try
        {
            FileUtils.copyFile(screenshot, new File(path));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void saveFailScenarioScreenshot(WebDriver driver, String scenario)
    {
        String path = String.format(FAIL_SCENARIO_PATH, scenario.replace("/","")+"-"+JavaInfastructure.getNumber());
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        saveScreenshot(driver, path);
    }
}

