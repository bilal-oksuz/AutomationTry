package com.Pages;

import com.Common.Base.JavaInfastructure;
import com.Common.Clients.RestTrigger;
import com.Common.UI.PageBase;
import com.mashape.unirest.http.HttpResponse;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainPage extends PageBase {

    @FindBy(how = How.CLASS_NAME, using = "user-login-container")
    private WebElement loginDrop;

    @FindBy(how = How.CLASS_NAME, using = "search-box-container")
    private WebElement searchBox;

    @FindBy(how = How.CLASS_NAME, using = "account-nav-item basket-preview")
    private WebElement basket;

    @FindBy(how = How.CLASS_NAME, using = "account-favorites")
    private WebElement favorites;

    @FindBy(className = "component-big-list")
    private WebElement butiqueBig;

    @FindBy(className = "component-small-list")
    private WebElement butiqueSmall;

    @FindBy(className = "search-icon")
    private WebElement searchStart;

    @FindBy(className = "login-button")
    private WebElement loginButton;

    @FindBy(className = "signup-button")
    private WebElement registerButton;

    @FindBy(className = "scroll-to-up")
    private WebElement scrollToUp;

    @FindBy(how = How.CLASS_NAME, using = "fancybox-close")
    private WebElement dialoguePopup;

    @FindBy(how = How.CLASS_NAME, using = "loggedin-account-item")
    private List<WebElement> loggedMenu;

    private List<WebElement> butiqueSmallList;
    private List<WebElement> butiqueBigList;

    @FindBy(how = How.CLASS_NAME, using = "modal-close")
    private WebElement newCustomerPopup;


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void getMainPage()
    {
        navigateUrl("https://www.trendyol.com");
        closeDialoguePopup();
        closeNewCustomerPopup();
    }

    public void hoverLogin() {
        hoverElement(loginDrop);
    }

    public void logout() {
        hoverAndClick(loginDrop,loggedMenu.get(7));
    }

    public void hoverAndClickLogin() {
        hoverAndClick(loginDrop, loginButton);
    }

    public void hoverAndClickRegister() {
        hoverAndClick(loginDrop,registerButton);
    }

    public void scrollOnPage(int length)
    {
        scrollDown(length);
    }

    public boolean checkCustomerLogout()
    {
        if(loginDrop.getText().equals("Giriş Yap"))
        {
            return true;
        }
        return false;
    }

    public void closeNewCustomerPopup()
    {
        if(!JavaInfastructure.isNull(waitForElementVisible(newCustomerPopup)))
        {
            newCustomerPopup.click();
        }
    }

    public void closeDialoguePopup()
    {
        if (!JavaInfastructure.isNull(waitForElementVisible(dialoguePopup))) {
            dialoguePopup.click();
        }
    }

    public void getAllButiqueData() {
        scrollOnPage(10000);
        ArrayList<String> butiqueUrls = new ArrayList<>();
        setButiqueBigList(butiqueBig.findElements(By.className("component-item")));
        setButiqueSmallList(butiqueSmall.findElements(By.className("component-item")));
        for (WebElement element : getButiqueBigList()) {
            List<WebElement> list = element.findElements(By.tagName("a"));
            for (WebElement butiqueBigElement : list) {
                String value = butiqueBigElement.getAttribute("href");
                butiqueUrls.add(value);
            }
        }
        for (WebElement element : getButiqueSmallList()) {
            List<WebElement> list = element.findElements(By.tagName("a"));
            for (WebElement butiqueSmallElement : list) {
                String value = butiqueSmallElement.getAttribute("href");
                butiqueUrls.add(value);
            }
        }
        scrollToUp.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createFile(butiqueUrls);
    }

    public WebElement getLoginDrop() {
        return loginDrop;
    }

    public void setLoginDrop(WebElement loginDrop) {
        this.loginDrop = loginDrop;
    }

    public WebElement getSearchBox() {
        return searchBox;
    }

    public void setSearchBox(WebElement searchBox) {
        this.searchBox = searchBox;
    }

    public WebElement getBasket() {
        return basket;
    }

    public void setBasket(WebElement basket) {
        this.basket = basket;
    }

    public WebElement getFavorites() {
        return favorites;
    }

    public void setFavorites(WebElement favorites) {
        this.favorites = favorites;
    }

    public WebElement getButiqueBig() {
        return butiqueBig;
    }

    public void setButiqueBig(WebElement butiqueBig) {
        this.butiqueBig = butiqueBig;
    }

    public WebElement getButiqueSmall() {
        return butiqueSmall;
    }

    public void setButiqueSmall(WebElement butiqueSmall) {
        this.butiqueSmall = butiqueSmall;
    }

    public WebElement getSearchStart() {
        return searchStart;
    }

    public void setSearchStart(WebElement searchStart) {
        this.searchStart = searchStart;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(WebElement loginButton) {
        this.loginButton = loginButton;
    }

    public WebElement getDialoguePopup() {
        return dialoguePopup;
    }

    public void setDialoguePopup(WebElement dialoguePopup) {
        this.dialoguePopup = dialoguePopup;
    }

    public List<WebElement> getButiqueSmallList() {
        return butiqueSmallList;
    }

    public void setButiqueSmallList(List<WebElement> butiqueSmallList) {
        this.butiqueSmallList = butiqueSmallList;
    }

    public List<WebElement> getButiqueBigList() {
        return butiqueBigList;
    }

    public void setButiqueBigList(List<WebElement> butiqueBigList) {
        this.butiqueBigList = butiqueBigList;
    }

    public WebElement getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(WebElement registerButton) {
        this.registerButton = registerButton;
    }

    public List<WebElement> getLoggedMenu() {
        return loggedMenu;
    }

    public void setLoggedMenu(List<WebElement> loggedMenu) {
        this.loggedMenu = loggedMenu;
    }

    public WebElement getNewCustomerPopup() {
        return newCustomerPopup;
    }

    public void setNewCustomerPopup(WebElement newCustomerPopup) {
        this.newCustomerPopup = newCustomerPopup;
    }

    public void checkImageLogFromNetworkLog(WebDriver driver) {

        OutputStream logfile = null;
        try
        {
            logfile = new FileOutputStream(new File(new
                    File(".").getCanonicalPath() + "/log.txt"), true);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        PrintStream printlog = new PrintStream(logfile);
        LogEntries logs = driver.manage().logs().get(LogType.PERFORMANCE);
        for (LogEntry entry : logs) {
            if (entry.toString().contains("\"type\":\"Image\"") && entry.toString().contains("\"method\":\"Network.responseReceived\""))
            {
                String log=entry.toString();
                System.out.println(entry.toString());
                log=StringUtils.replaceAll(log,"(.*)INFO(.)", "");
                printlog.append(log+"\n");
            }
        }
        printlog.close();
    }

    public static void capture(WebDriver driver) throws IOException {
        OutputStream logfile = new FileOutputStream(new File(new
                File(".").getCanonicalPath() + "/log.txt"), true);
        PrintStream printlog = new PrintStream(logfile);
        LogEntries logs = driver.manage().logs().get(LogType.PERFORMANCE);
        for (LogEntry entry : logs) {
            if (entry.toString().contains("\"type\":\"Image\"") && entry.toString().contains("\"method\":\"Network.responseReceived\"") )
            {
                System.out.println(entry.toString());
                printlog.append(new Date(entry.getTimestamp()) + " " + entry.toString() + " "
                        + System.getProperty("line.separator"));
                printlog.append(System.getProperty("line.separator"));
                printlog.append(System.getProperty("line.separator"));
            }
        }
        printlog.close();
    }

    public void createFile(ArrayList<String> butiqueUrls)
    {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/Csv/ButikLink.csv";
        JavaInfastructure.createFile(filePath, "Butik Linki|Response Time");
        for (String url : butiqueUrls) {
            RestTrigger restTrigger = new RestTrigger();
            HttpResponse<String> response = restTrigger.SendRequestGet(url);
            JavaInfastructure.createFile(filePath, url + "|" + response.getStatus());
        }
        /* Dosya slack kanalına gönderilmek istenirse bir slack api ilgili kanala eklenip configte token değeri doldurulmalıdır.
        Slack slack=new Slack();
        slack.fileUpload(new File(filePath),"slack channel id");
        */
    }
}
