package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends TestBase {

    @FindBy(xpath = "//span[text()='Log In']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//div[text()='Login']")
    private WebElement login;

    @FindBy(xpath = "//a[text()='Sign Up']")
    private WebElement register;

    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

    //Actions
    public String validateLoginPageTitle(){
        return driver.getTitle();
    }

    public HomePage login(String username, String password){
        loginButton.click();
        email.sendKeys(username);
        this.password.sendKeys(password);
        login.click();
        return new HomePage();
    }

}
