package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ContactsPage extends TestBase {
    @FindBy(xpath = "//span[@class='selectable ' and text()='Contacts']")
    private WebElement contactsLabel;

    @FindBy(xpath = "//div[@name='status' and @role='listbox']")
    private WebElement statusList;

    @FindBy(xpath = "//input[@name='first_name']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@name='last_name']")
    private WebElement lastName;
    @FindBy(xpath = "//label[contains(text(),'Company')]/following-sibling::div[@name='company']")
    private WebElement companyDropdown;

    @FindBy(xpath = "//label[contains(text(),'Company')]/following-sibling::div[@name='company']//input[@class='search']")
    private WebElement companySearchText;

    @FindBy(xpath = "//button[@class='ui linkedin button']//i[@class='save icon']")
    private WebElement contactsSaveButton;

    @FindBy(xpath = "//i[@class='refresh icon']")
    private WebElement refreshContacts;

    TestUtil testUtil = new TestUtil();


    public ContactsPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean verifyContactsLabel() {
        return contactsLabel.isDisplayed();
    }

    public void selectContacts(String name) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Click refresh and wait for it to be stale (table reloads)
        testUtil.explicitWait.until(ExpectedConditions.elementToBeClickable(refreshContacts)).click();

        // Wait for the row with the contact name to appear
        WebElement checkbox = testUtil.explicitWait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[contains(text(),'" + name + "')]"
                                + "/parent::td"
                                + "/preceding-sibling::td"
                                + "//input[@name='id']")
                )
        );

        // JS click bypasses ElementClickInterceptedException on hidden inputs
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);

    }

    public void selectContactType(String contactType) {
        // Step 1: Click the dropdown to open it
        statusList.click();

        // Step 2: Click the option by visible text
        WebElement SelectStatus = testUtil.explicitWait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@name='status']//span[@class='text' and text()='" + contactType + "']")));
        SelectStatus.click();
    }

    public void selectCompany(String companyName) {


// Open dropdown
        companyDropdown.click();
//Type search text
        testUtil.explicitWait.until(ExpectedConditions.elementToBeClickable(companySearchText)).sendKeys(companyName);
// Pick first matching option
        testUtil.explicitWait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[contains(text(),'" + companyName + "')]"))).click();
    }
    public void createNewContact(String fName,String lName,String contactType, String companyName) throws InterruptedException {
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        selectContactType(contactType);
        selectCompany(companyName);

        contactsSaveButton.click();
        Thread.sleep(2000);
    }
}
