package com.testninja.pageobjects.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WaitHandler {

    WebDriver driver;
    private int defaultWaitTime = 20;

    public WaitHandler(WebDriver driver) {
        this.driver = driver;
    }

    public void waitFor(ExpectedCondition condition) {
        new WebDriverWait(driver, defaultWaitTime).until(condition);
    }

    public WebElement waitForElementToDisplay(WebElement webElement) {
        return waitForElementToDisplay(webElement, defaultWaitTime);
    }

    public WebElement waitForElementToDisplay(WebElement webElement, long waitTime) {
        return new WebDriverWait(driver, waitTime).until(ExpectedConditions.visibilityOf(webElement));
    }


    public void waitForElementToBeClickable(WebElement webElement) {
        waitForElementToBeClickable(webElement, defaultWaitTime);
    }

    public void waitForElementToBeClickable(WebElement webElement, long waitTime) {
        new WebDriverWait(driver, waitTime).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitForElementToBePresent(WebElement webElement) {
        waitForElementToBePresent(webElement, defaultWaitTime);
    }

    public void waitForElementToBePresent(WebElement webElement, long waitTime) {
        new WebDriverWait(driver, waitTime).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                try {
                    webElement.isDisplayed();
                    return webElement;
                } catch (NoSuchElementException e) {
                    return null;
                }
            }
        });
    }

    public List<WebElement> waitForElementsToDisplay(List<? extends WebElement> webElements) {
        return waitForElementsToDisplay((List<WebElement>) webElements, defaultWaitTime);
    }

    public void waitForElement(By locator) {
        for (int i = 0; i < defaultWaitTime; i++) {
            try {
                driver.findElement(locator).isDisplayed();
                return;
            } catch (Exception e) {
                sleep(1000);
            }
        }
    }

    public List<WebElement> waitForElementsToDisplay(List<? extends WebElement> webElements, long waitTime) {
        return new WebDriverWait(driver, waitTime).until(ExpectedConditions.visibilityOfAllElements((List<WebElement>) webElements));
    }

    public void waitForPageToLoad() {
        waitForPageToLoad(defaultWaitTime);
    }

    public void waitForPageToLoad(int seconds) {
        try {
            new WebDriverWait(driver, seconds).until((WebDriver) -> {
                return String.valueOf(executeJavascript("return document.readyState"))
                        .equals("complete");
            });
        } catch (TimeoutException ex) {
            // don't throw if page is still loading. Some pages never
            // archive readyState == complete, but are functionaly correct
        }
    }

    public void sleep(int timeInMilliSeconds) {
        executeScriptAsync("var callback = arguments[arguments.length-1]; setTimeout(function(){ callback() }, arguments[0]);", timeInMilliSeconds);
    }

    /* Execute Javascript */
    public Object executeJavascript(String script, Object... arguments) {
        return ((JavascriptExecutor) driver).executeScript(script, arguments);
    }

    public void executeScriptAsync(String script, Object... arguments) {
        ((JavascriptExecutor) driver).executeAsyncScript(script, arguments);
    }
}
