package com.Pages;

import com.Common.Base.Customer;
import com.Common.UI.PageBase;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPage extends PageBase {

    @FindBy(id="register-email")
    private WebElement emailBox;

    @FindBy(id="register-password-input")
    private WebElement passwordBox;

    @FindBy(how = How.CLASS_NAME, using = "female")
    private WebElement femaleSelection;

    @FindBy(how = How.CLASS_NAME, using = "male")
    private WebElement maleSelection;


    @FindBy(how = How.CLASS_NAME, using = "submit")
    private WebElement submitButton;


    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void submitForm()
    {
        submitButton.click();
    }

    public void setCustomerInformation()
    {
        sendKeys(emailBox, "bilal.oksuz@tr.com");
        sendKeys(passwordBox, "12345678");
        click(maleSelection);
    }

    public void setCustomerInformation(Customer customer)
    {
        sendKeys(emailBox, customer.getUserMail());
        sendKeys(passwordBox, customer.getUserPass());
        click(maleSelection);
    }

}
