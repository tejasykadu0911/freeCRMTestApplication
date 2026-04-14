package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class HomePage extends TestBase {

    @FindBy(xpath = "//span[@class='user-display' and text()='Tejas kadu']")
    private WebElement userNameLabel;

    @FindBy(xpath = "//span[@class='item-text' and text()='Contacts']")
    private WebElement contactsLink;

    @FindBy(xpath = "//button[@class='ui linkedin icon button']//i[@class='refresh icon']")
    private WebElement contactsRefreshButton;

    @FindBy(xpath = "//span[@class='item-text' and text()='Deals']")
    private WebElement dealsLink;

    @FindBy(xpath = "//span[@class='item-text' and text()='Tasks']")
    private WebElement tasksLink;

    @FindBy(xpath = "//a[@href='/contacts']/following-sibling::button//i[contains(@class,'plus')]")
    private WebElement addContactsFromHomePageButton;




    public HomePage(){
        PageFactory.initElements(driver,this);
    }

    public String verifyHomaPageTitle(){
        return driver.getTitle();
    }

    public boolean verifyCorrectUserName(){
        return userNameLabel.isDisplayed();
    }

    public ContactsPage clickOnContactsLink(){
        contactsLink.click();
        return new ContactsPage();
    }

    public DealsPage clickOnDealsLink(){
        dealsLink.click();
        return new DealsPage();
    }
    public TasksPage clickOnTasksLink(){
        tasksLink.click();
        return new TasksPage();
    }

    public void clickOnNewContactButton(){
       Actions actions = new Actions(driver);
       actions.moveToElement(contactsLink).build().perform();
       new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(addContactsFromHomePageButton));
        addContactsFromHomePageButton.click();
    }
}