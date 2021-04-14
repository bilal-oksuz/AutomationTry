package com.Pages;

import com.Common.Base.Customer;
import com.Common.Base.JavaInfastructure;
import com.Common.UI.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class LoginPage extends PageBase {

    @FindBy(how = How.CLASS_NAME, using = "header-buttons")
    private List<WebElement> pageMenu;

    @FindBy(id = "login-email")
    private WebElement emailBox;

    @FindBy(id = "login-password-input")
    private WebElement passwordBox;

    @FindBy(className = "forgot-passwordinput")
    private WebElement forgotPassword;

    @FindBy(how = How.CLASS_NAME, using = "submit")
    private WebElement loginButton;


    @FindBy(id = "error-box-wrapper")
    private WebElement errorMessage;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setCustomerInformation(Customer customer)
    {
        sendKeys(emailBox, customer.getUserMail());
        sendKeys(passwordBox, customer.getUserPass());
    }

    public void submitForm()
    {
        loginButton.click();
    }

    public boolean isHasError()
    {
        if(JavaInfastructure.isNull(waitForElementVisible(errorMessage)))
        {
            return false;
        }
        return true;
    }

    public boolean errorMessage(Customer customer)
    {
        return errorMessage.getText().equals(customer.invalidCustomer(customer));
    }

    public String errorMessage()
    {
        return errorMessage.getText();
    }


    public void goRegisterPage()
    {
        click(pageMenu.get(0).findElements(By.className("q-secondary")).get(1));
    }
}
