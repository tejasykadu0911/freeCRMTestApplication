package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.modules.contacts.ContactsObject;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static com.crm.qa.util.TestUtil.createContactsListOfObjects;

public class ContactsPageTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    ContactsPage contactsPage;

    public ContactsPageTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        try{
            initialization();
            contactsPage = new ContactsPage();
            loginPage = new LoginPage();
            homePage= loginPage.login( prop.getProperty("username"),prop.getProperty("password"));
            homePage.clickOnContactsLink();
        }catch (Exception e){
            driver.quit();
        }

    }

    @Test(priority = 1)
    public void verifyContactsPageLabel(){
        Assert.assertTrue(contactsPage.verifyContactsLabel(),"Contacts label is missing");
    }

    @Test(priority = 2)
    public void selectContactsTest() throws InterruptedException {
        contactsPage.selectContacts("tejassss kaduuuu");
    }

    @DataProvider(name ="contactsObjectList")
    public Object[][] getContactsList() throws IOException {
        return createContactsListOfObjects();
    }


    @Test(priority = 3,dataProvider = "contactsObjectList")
    public void createNewContact(ContactsObject contact) throws InterruptedException {
        homePage.clickOnNewContactButton();
        //contactsPage.createNewContact("tejas5","kadu5","New","codametrix1");
        System.out.println(contact.getFirstName());
        System.out.println(contact.getLastName());
        System.out.println(contact.getContactType());
        System.out.println(contact.getCompanyName());

        contactsPage.createNewContact(contact.getFirstName(), contact.getLastName(),contact.getContactType() ,contact.getCompanyName());
    }

    @AfterMethod
    public void tearDown(){
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.out.println("Driver already closed or unreachable: " + e.getMessage());
        }

    }

}
