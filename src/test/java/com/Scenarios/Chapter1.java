package com.Scenarios;

import com.Common.Base.Config;
import com.Common.Base.Customer;
import com.Common.Base.JavaInfastructure;
import com.Common.Base.ScreenshotManager;
import com.Common.Clients.MongoDB;
import com.Common.Clients.RestTrigger;
import com.Common.UI.SetDriver;
import com.Pages.LoginPage;
import com.Pages.MainPage;
import com.Pages.RegisterPage;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Chapter1
{
    MongoDB mongoDB;
    RestTrigger restTrigger;
    WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    RegisterPage registerPage;
    Customer customer;
    Config config;
    ScreenshotManager screenshotManager;

    public Chapter1()
    {
        /* localden koşum için gerekli datalar
        Config config=new Config().config("production");
        SetDriver setDriver=new SetDriver("chrome","linux");
        */

        mongoDB = new MongoDB();
        restTrigger = new RestTrigger();
        driver = SetDriver.getSetDriver().getDriver();
        mainPage=new MainPage(driver);
        loginPage=new LoginPage(driver);
        registerPage=new RegisterPage(driver);
    }

    @Given("^Get Trendyol landing page$")
    public void getTrendyolLandingPage()
    {
        mainPage.getMainPage();
        if(!mainPage.checkCustomerLogout())
        {
            mainPage.logout();
        }
    }

    @When("^Save all boutique link to csv$")
    public void saveBoutiqueData()
    {
        mainPage.getAllButiqueData();
    }

    @When("^Scroll on page and save Image response$")
    public void saveScroolAndImageResponse()
    {
        mainPage.clearNetworkLog();
        mainPage.scrollOnPage(500);
        mainPage.checkImageLogFromNetworkLog(driver);
    }

    @When("^As a customer login to Trendyol$")
    public void asACustomerlogintoTrendyol()
    {
        mainPage.hoverAndClickLogin();
    }

    @When("^Select register flow$")
    public void asACustomerRegistertoTrendyol()
    {
        mainPage.hoverAndClickRegister();
        registerPage.setCustomerInformation();
        registerPage.submitForm();
    }

    @Then("^Login with user \"([^\"]*)\"$")
    public void selectValidUserData(String user)
    {
        customer=new Customer(user);
        loginPage.setCustomerInformation(customer);
        loginPage.submitForm();
    }

    @Then("^Check login process$")
    public void checkUserLoginStatus()
    {
        if(loginPage.isHasError())
        {
            Assert.assertTrue(loginPage.isHasError());
            Assert.assertTrue(loginPage.errorMessage(customer));
            ScreenshotManager.saveFailScenarioScreenshot(driver, loginPage.errorMessage());

        }
        else
        {
            Assert.assertEquals(mainPage.getLoginDrop().getText(),"Hesabım");
            mainPage.closeNewCustomerPopup();
        }
    }



}







