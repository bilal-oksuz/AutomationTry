package com.Common.UI;

import com.Common.Base.Config;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;


public class SetDriver
{
    public String browser;
    public String platform;
    public String browserVersion;
    public WebDriver driver;
    public RemoteWebDriver remoteWebDriver;
    String driverPath=System.getProperty("user.dir")+"/bin/drivers/";
    Config config= Config.getConfig();
    static SetDriver setDriver;

    public SetDriver()
    {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        LoggingPreferences loggingPreferences=new LoggingPreferences();

        switch (platform)
        {
            case "windows":
                driverPath+=platform+"/"+browser+"driver.exe";
                break;
            case "macos":
            case "linux":
                driverPath+=platform+"/"+browser+"driver";
                break;
        }

        switch (browser)
        {
            case "chrome":
                System.setProperty("webdriver.chrome.driver",driverPath);
                loggingPreferences.enable(LogType.PERFORMANCE,Level.ALL);
                desiredCapabilities.setCapability("goog:loggingPrefs", loggingPreferences);
                desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
                if (!config.getGridFeature())
                {
                    ChromeOptions options = new ChromeOptions();
                    options.merge(desiredCapabilities);
                    driver=new ChromeDriver(options);
                }
                break;
            case "firefox":
                driverPath=driverPath.replace("firefox","gecko");
                System.setProperty("webdriver.gecko.driver",driverPath);
                desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
                FirefoxOptions firefoxOptions=new FirefoxOptions();
                firefoxOptions.merge(desiredCapabilities);

                if (!config.getGridFeature())
                {
                    driver= new FirefoxDriver(firefoxOptions);
                }
                break;
            case "safari":
                desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.SAFARI);
                SafariOptions safariOptions=new SafariOptions();
                safariOptions.merge(desiredCapabilities);
                if (!config.getGridFeature())
                {
                    driver= new SafariDriver(safariOptions);
                }
                break;
        }

        if(config.getGridFeature())
        {
            try
            {
                URL url = new URL(config.getGridURL());
                driver = new RemoteWebDriver(url,desiredCapabilities);
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
        }
        setSetDriver(this);

    }



    public SetDriver(String browser,String platform)
    {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        LoggingPreferences loggingPreferences=new LoggingPreferences();

        switch (platform)
        {
            case "windows":
                driverPath+=platform+"/"+browser+"driver.exe";
                desiredCapabilities.setPlatform(Platform.WINDOWS);
                break;
            case "macos":
                driverPath+=platform+"/"+browser+"driver";
                desiredCapabilities.setPlatform(Platform.MAC);
                break;
            case "linux":
                driverPath+=platform+"/"+browser+"driver";
                desiredCapabilities.setPlatform(Platform.LINUX);
                break;
        }
        switch (browser)
        {
            case "chrome":
                System.setProperty("webdriver.chrome.driver",driverPath);
                loggingPreferences.enable(LogType.PERFORMANCE,Level.ALL);
                desiredCapabilities.setCapability("goog:loggingPrefs", loggingPreferences);
                desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
                if (!config.getGridFeature())
                {
                    ChromeOptions options = new ChromeOptions();
                    options.merge(desiredCapabilities);
                    driver=new ChromeDriver(options);
                }
                break;
            case "firefox":
                driverPath=driverPath.replace("firefox","gecko");
                System.setProperty("webdriver.gecko.driver",driverPath);
                desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
                if (!config.getGridFeature())
                {
                    FirefoxOptions firefoxOptions=new FirefoxOptions();
                    firefoxOptions.merge(desiredCapabilities);
                    driver= new FirefoxDriver(firefoxOptions);
                }
                break;
            case "opera":
                desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.OPERA_BLINK);
                if (!config.getGridFeature())
                {
                    OperaOptions operaOptions=new OperaOptions();
                    operaOptions.merge(desiredCapabilities);
                    driver= new OperaDriver(operaOptions);
                }
                break;
            case "edge":
                desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.EDGE);
                if (!config.getGridFeature())
                {
                    EdgeOptions edgeOptions=new EdgeOptions();
                    edgeOptions.merge(desiredCapabilities);
                    driver= new EdgeDriver(edgeOptions);
                }
                break;
        }

        if(config.getGridFeature())
        {
            try
            {
                URL url = new URL(config.getGridURL());
                System.out.println(config.getGridURL());
                driver = new RemoteWebDriver(url,desiredCapabilities);
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
        }
        setSetDriver(this);
    }


    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

//    public WebDriver getDriver() {
//        return driver;
//    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public static SetDriver getSetDriver() {
        return setDriver;
    }

    public static void setSetDriver(SetDriver setDriver) {
        SetDriver.setDriver = setDriver;
    }
}
