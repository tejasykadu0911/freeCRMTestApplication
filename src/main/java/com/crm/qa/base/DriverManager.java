package com.crm.qa.base;

import com.crm.qa.util.WebEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import static com.crm.qa.base.TestBase.prop;

public class DriverManager {

    //public static WebDriver driver;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver(String browser) {
        WebDriver webDriver;

        switch (browser.toLowerCase()) {
            case "firefox":
                webDriver = new FirefoxDriver();
                break;
            case "chrome":
            default:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-notifications");
                // Uncomment for Jenkins/CI
                // options.addArguments("--headless");
                // options.addArguments("--no-sandbox");
                // options.addArguments("--disable-dev-shm-usage");
                webDriver = new ChromeDriver(options);
                break;
        }

        // Attach event listener
        WebEventListener listener = new WebEventListener();
        WebDriver decoratedDriver = new EventFiringDecorator<>(listener).decorate(webDriver);

        driver.set(decoratedDriver);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // Important: prevents memory leaks
        }
    }
}
