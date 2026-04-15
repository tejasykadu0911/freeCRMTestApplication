package com.crm.qa.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.util.logging.Logger;

public class WebEventListener implements WebDriverListener {

    private static final Logger log = Logger.getLogger(WebEventListener.class.getName());

    // ==================== NAVIGATION ====================

    @Override
    public void beforeGet(WebDriver driver, String url) {
        log.info("Navigating to: " + url);
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        log.info("Successfully navigated to: " + url);
    }

    // ==================== CLICK ====================

    @Override
    public void beforeClick(WebElement element) {
        log.info("Clicking on element: " + element.toString());
    }

    @Override
    public void afterClick(WebElement element) {
        log.info("Successfully clicked on element: " + element.toString());
    }

    // ==================== SEND KEYS ====================

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        log.info("Typing into element: " + element.toString());
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        log.info("Successfully typed into element: " + element.toString());
    }

    // ==================== FIND ELEMENT ====================

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        log.info("Finding element by: " + locator.toString());
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        log.info("Found element by: " + locator.toString());
    }

    // ==================== NAVIGATION BACK/FORWARD ====================

    @Override
    public void beforeBack(WebDriver.Navigation navigation) {
        log.info("Navigating back");
    }

    @Override
    public void afterBack(WebDriver.Navigation navigation) {
        log.info("Successfully navigated back");
    }

    @Override
    public void beforeForward(WebDriver.Navigation navigation) {
        log.info("Navigating forward");
    }

    @Override
    public void afterForward(WebDriver.Navigation navigation) {
        log.info("Successfully navigated forward");
    }

    // ==================== REFRESH ====================

    @Override
    public void beforeRefresh(WebDriver.Navigation navigation) {
        log.info("Refreshing page");
    }

    @Override
    public void afterRefresh(WebDriver.Navigation navigation) {
        log.info("Successfully refreshed page");
    }

    // ==================== QUIT/CLOSE ====================

    @Override
    public void beforeQuit(WebDriver driver) {
        log.info("Closing browser");
    }

    @Override
    public void afterQuit(WebDriver driver) {
        log.info("Browser closed successfully");
    }

    // ==================== EXCEPTIONS ====================

//    @Override
//    public void onError(Object target, java.lang.reflect.Method method,
//                        Object[] args, org.openqa.selenium.support.events.InvocationTargetException e) {
//        log.severe("Error occurred on method: " + method.getName());
//        log.severe("Exception: " + e.getCause().getMessage());
//    }
}