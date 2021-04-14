package com.Common.Base;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.util.List;

public class Customer
{
    public String userMail;
    public String userPass;
    public String gender;
    public transient Config config=Config.getConfig();

    public Customer() {
    }

    public void validCustomer()
    {

    }

    public Customer(String userType)
    {
        List<String> users=JavaInfastructure.readFile(System.getProperty("user.dir")+"/Customer.txt");
        for (int i = 0; i <users.size() ; i++) {
            String[] user= StringUtils.split(users.get(i),"|");
            if(userType.equals(user[2]))
            {
                setUserMail(user[0]);
                setUserPass(user[1]);
                break;
            }
        }
    }
    public Customer(String userMail, String userPass) {
        this.userMail = userMail;
        this.userPass = userPass;
        this.gender="male";
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String invalidCustomer(Customer customer)
    {
        if(!customer.getUserMail().contains("@") || JavaInfastructure.isNullOrEmpty(customer.getUserMail()))
        {
            return "Lütfen geçerli bir e-posta adresi giriniz.";
        }
        else if(JavaInfastructure.isNullOrEmpty(customer.getUserPass()))
        {
            return "Lütfen şifrenizi giriniz.";
        }
        return "E-posta adresiniz ve/veya şifreniz hatalı.";
    }
}
