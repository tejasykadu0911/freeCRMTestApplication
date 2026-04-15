package com.crm.qa.base;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
    public static EventFiringWebDriver e_driver;
    public static WebEventListener eventListener;


//    Listener Interface: Replace WebDriverEventListener with org.openqa.selenium.support.events.WebDriverListener.
//    Firing Mechanism: Replace EventFiringWebDriver with EventFiringDecorator<WebDriver>.

    public TestBase(){
        try{
            prop = new Properties();
            FileInputStream ip = new FileInputStream("/Users/tejaskadu/git/" +
                    "FreeCRMTestAutomation/src/main/java/com/crm/qa/config/config.properties");

            prop.load(ip);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initialization(){
        String browserName = prop.get("browser").toString();

        if(browserName.equals("chrome")){
            //System.setProperty("Webdriver.chrome.driver","")
            driver = new ChromeDriver();
        }else if(browserName.equals("firefox")) {
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));

        driver.get(prop.get("url").toString());
    }



}
