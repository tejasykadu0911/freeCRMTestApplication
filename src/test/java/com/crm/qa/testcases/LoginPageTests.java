package com.crm.qa.testcases;

import com.crm.qa.Analyzer.RetryAnalyzer;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class LoginPageTests extends TestBase {

    LoginPage loginPage;
    HomePage homePage;


    Logger logger = Logger.getLogger(LoginPageTests.class);

    public LoginPageTests(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        logger.info("loggin page");
        loginPage = new LoginPage();
    }

//    @Test(priority = 1,retryAnalyzer = RetryAnalyzer.class)
    @Test(priority = 1)
    public void loginPageTitleTest(){
        String title = loginPage.validateLoginPageTitle();
        Assert.assertEquals(title,"#2 Free CRM Business Software - Free Forever");
    }

    @Test(priority = 2)
    public void loginTest(){
        homePage = loginPage.login( prop.getProperty("username"),prop.getProperty("password"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


}
