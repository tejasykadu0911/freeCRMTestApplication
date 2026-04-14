package com.crm.qa.util;

import com.crm.qa.modules.contacts.ContactsObject;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.crm.qa.base.TestBase.driver;
import static com.crm.qa.util.Constants.contactsDataExcel;

public class TestUtil {

    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 10;

    public WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));



    public static Object[][] createContactsListOfObjects() throws IOException {

        FileInputStream file = new FileInputStream(contactsDataExcel);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet("newContacts");

        List<ContactsObject> contactsObjectList = new ArrayList<>();

        for(int i = 0 ;i<sheet.getLastRowNum();i++){
            ContactsObject contactsObject = new ContactsObject();
            contactsObject.setFirstName(sheet.getRow(i+1).getCell(0).toString());
            contactsObject.setLastName(sheet.getRow(i+1).getCell(1).toString());
            contactsObject.setCompanyName(sheet.getRow(i+1).getCell(2).toString());
            contactsObject.setContactType(sheet.getRow(i+1).getCell(3).toString());
            contactsObjectList.add(contactsObject);
        }
        Object[][] data = new Object[contactsObjectList.size()][1];
        for (int i = 0; i < contactsObjectList.size(); i++) {
            data[i][0] = contactsObjectList.get(i); // Each row contains one object
        }
        return data;
    }

    public static void takeScreenshotAtEndOfTest() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        System.out.println("currentDir " + currentDir);
        FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
    }
}
