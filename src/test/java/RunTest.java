import com.Common.Base.Config;
import com.Common.Base.JavaInfastructure;
import com.Common.Base.ScreenshotManager;
import com.Common.Clients.RestTrigger;
import com.Common.UI.SetDriver;
import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;


@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resources/features",
        format = { "pretty",
                "html:target/reports/cucumber-pretty",
                "json:target/json-report/cucumber.json" }
)
public class RunTest {

    @BeforeClass()
    public static void setEnvironment()
    {
        System.out.println("environment ataması başladı");
        String env=System.getProperty("env");
        if(JavaInfastructure.isNullOrEmpty(env))
        {
            env="production";
            System.out.println("Environment bilgisi bulunamadı. Default değer atandı.");
        }
        Config config=new Config().config(env);
        System.out.println("browser ataması başladı");
        String browser=System.getProperty("browser");
        System.out.println("browser bilgisi: "+browser);
        if(JavaInfastructure.isNullOrEmpty(browser))
        {
            browser="chrome";
            System.out.println("Browser bilgisi bulunamadı. Default değer atandı.");
        }
        RestTrigger restTrigger=new RestTrigger();
        SetDriver setDriver=new SetDriver(browser,"linux");
        System.out.println("browser bilgisi: "+browser);
    }

    @AfterClass
    public static void quitBrowser()
    {
        SetDriver.getSetDriver().getDriver().quit();
    }

}
