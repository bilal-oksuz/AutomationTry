package com.Common.UI;

import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import gherkin.lexer.Th;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.NoSuchElementException;


public abstract class PageBase {


    protected static WebDriver driver;
    private Actions action;
    private WebDriverWait waitClick;
    private WebDriverWait waitVisibility;
    private JavascriptExecutor js;

    protected PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(driver), this);
        action= new Actions(driver);
        waitClick= new WebDriverWait(driver, 20);
        waitVisibility= new WebDriverWait(driver, 60);
        js = (JavascriptExecutor) driver;

    }


    protected PageBase() {
    }

    public void hoverElement(WebElement elementHover)
    {
        action.moveToElement(waitForElementVisible(elementHover)).perform();
    }


    public void assertTrue(boolean condition, String message) {
        Assert.assertTrue(message, condition);
    }


    public void navigateUrl(String url) {
        driver.get(url);
        waitUntilPageFullyLoad();
        driver.manage().window().maximize();
    }

    public void waitUntilPageFullyLoad() {
        new WebDriverWait(driver, 150).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public WebElement clickReady(WebElement element)
    {
        waitClick.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public void click(WebElement element)
    {
        waitClick.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public String getText(WebElement element)
    {
        waitVisibility.until(ExpectedConditions.invisibilityOf(element));
        return element.getText();
    }

    public void hoverAndClick(WebElement elementHover,WebElement clickElement)
    {
        action.moveToElement(waitForElementVisible(elementHover)).perform();
        waitForElementVisible(clickElement).click();
    }

    public void hoverAndClick(WebElement element)
    {
        action.moveToElement(waitForElementVisible(element)).perform();
        waitForElementVisible(element).click();
    }
    public void sendKeys(WebElement element,String value)
    {
        waitForElementVisible(element).sendKeys(value);
    }

    public boolean isDisplayed(WebElement element)
    {
        return isDisplayed(element);
    }

    public WebElement waitForElementVisible(WebElement element)
    {
        try {
            waitVisibility.until(ExpectedConditions.visibilityOf(element));
        }
        catch (Throwable e)
        {
            return null;
        }
        return element;
    }
    public void waitUntilUnvisibleWebElement(WebElement element)
    {
        waitVisibility.until(ExpectedConditions.invisibilityOf(element));
    }

    public void scrollDown(int y)
    {
        js.executeScript("window.scrollTo(0,"+y+")");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollDown()
    {
        js.executeScript("window.scrollTo(0,500)");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void get(WebDriver driver)
    {
        driver.get("https://www.trendyol.com");
    }

    public void get(WebDriver driver,String url)
    {
        driver.get(url);
    }

    public void getBack(WebDriver driver)
    {
        driver.navigate().back();
    }

    public void clearNetworkLog() {
        String script = "console.clear();";
        js.executeScript(script);
    }


    public static WebDriver getDriver(){
        return driver;
    }

}