package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    LoginPage loginPage;
    HomePage homePage;

    ContactsPage contactsPage;

    public HomePageTests(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        contactsPage = new ContactsPage();
        loginPage = new LoginPage();
        homePage= loginPage.login( prop.getProperty("username"),prop.getProperty("password"));
    }

    @Test(priority = 1)
    public void verifyHomePageTitleTest(){
        String title = homePage.verifyHomaPageTitle();
        Assert.assertEquals(title,"Free CRM");
    }

    @Test(priority = 2)
    public void verifyUserNameLableTest(){
        Assert.assertTrue(homePage.verifyCorrectUserName());
    }

    @Test(priority = 3)
    public void verifyContactsLinkTest(){
       contactsPage = homePage.clickOnContactsLink();
    }

    @Test(priority = 4)
    public void clickOnAddContactFromHomePage(){
     homePage.clickOnNewContactButton();

    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
