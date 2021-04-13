package com.testninja.pageobjects.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Interactions {

    WebDriver driver;
    public WaitHandler waitHandler;

    public Interactions(WebDriver driver) {
        this.driver = driver;
        waitHandler = new WaitHandler(driver);
    }

    public void refreshBrowser() {
        driver.navigate().refresh();
       
    }

    public void open(String url) {
        driver.navigate().to(url);
       
    }

    public Interactions mouseHover(WebElement element) {
       
        waitHandler.waitForElementToBePresent(element);
        new Actions(driver).moveToElement(element).moveToElement(element).build().perform();
        return this;
    }

    public Interactions mouseHover(WebElement element, int xOffset, int yOffset) {
       
        waitHandler.waitForElementToBePresent(element);
        new Actions(driver).moveToElement(element, xOffset, yOffset).build().perform();
        return this;
    }

    public Interactions mouseHoverOnInvisibleElement(WebElement element) {
       
        new Actions(driver).moveToElement(element).perform();
        return this;
    }

    public Interactions click(WebElement element) {
        waitHandler.waitForElementToBeClickable(element);
        element.click();
       
        return this;
    }

    public Interactions selectMultipleOption(List<WebElement> elements) {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL);
        for (WebElement element: elements) {
            actions.click(element);
        }
        actions.keyDown(Keys.CONTROL)
                .build()
                .perform();
       
        return this;
    }
    public Interactions mouseClick(WebElement element) {
        waitHandler.waitForElementToDisplay(element);
        new Actions(driver).moveToElement(element).click().build().perform();
       
        return this;
    }

    public Interactions mouseClickOnInvisibleElement(WebElement element) {
        new Actions(driver).moveToElement(element).click().perform();
       
        return this;
    }

    public Interactions mouseSet(WebElement element, String text) {
        new Actions(driver).sendKeys(element, text).perform();
        return this;
    }

    public Interactions javaScriptClick(WebElement element) {
        waitHandler.waitForElementToDisplay(element);
        executeScript("arguments[0].click()", element);
       
        return this;
    }

    public Interactions javaScriptClear(WebElement element) {
        executeScript("arguments[0].value=arguments[1]", element, "");
       
        return this;
    }

    public Interactions javaScriptType(WebElement element, String text) {
        executeScript("arguments[0].value=arguments[1]", element, text);
       
        return this;
    }

    public Interactions javaScriptClearAndType(WebElement element, String text) {
        javaScriptClear(element).javaScriptType(element, text);
        return this;
    }

    public void doubleClick(WebElement element) {
        waitHandler.waitForElementToDisplay(element);
        new Actions(driver).moveToElement(element).doubleClick().perform();
       
    }

    public void dragAndDrop(WebElement source, WebElement destination) {
        waitHandler.waitForElementToBeClickable(source);
        waitHandler.waitForElementToBeClickable(destination);
        new Actions(driver).clickAndHold(source).moveToElement(destination).release().perform();
    }

    public void dragAndDropOnInVisibleElement(WebElement source, WebElement destination) {
        new Actions(driver).clickAndHold(source).moveToElement(destination).release().perform();
    }

    public void dragAndDropBy(WebElement source, int x, int y) {
        waitHandler.waitForElementToBeClickable(source);
        new Actions(driver).dragAndDropBy(source, x, y).build().perform();
    }

    public void rightClick(WebElement element) {
        waitHandler.waitForElementToBeClickable(element);
        new Actions(driver).moveToElement(element).contextClick().perform();
    }

    public void type(WebElement element, String text) {
        if (text == null) {
            return;
        }
        element.sendKeys(text);
    }

    public void type(WebElement element, String[] text) {
        if (text == null) {
            return;
        }
        for (String val : text) {
            element.sendKeys(val);
            element.sendKeys(Keys.TAB);
        }
    }

    public void type(WebElement element, List<String> text) {
        if (text == null) {
            return;
        }
        for (String val : text) {
            element.sendKeys(val);
            element.sendKeys(Keys.TAB);
        }
    }

    public void type(WebElement webElement, double number) {
        type(webElement, Double.toString(number));
    }

    public Interactions clear(WebElement element) {
        element.clear();
        return this;
    }

    public Interactions clearAndType(WebElement webElement, String text) {
        waitHandler.waitForElementToBeClickable(webElement);
        clear(webElement);
        type(webElement, text);
        return this;
    }

    public Interactions switchToFrameByIndex(Integer index) {
       
        driver.switchTo().frame(index);
        return this;
    }

    public Interactions switchToDefaultContent() {
        driver.switchTo().defaultContent();
        return this;
    }
    public Interactions typeAndEnter(WebElement webElement, String text) {
        waitHandler.waitForElementToBeClickable(webElement);
        clear(webElement);
        type(webElement, text);
        pressEnter(webElement);
        return this;
    }

    public void clearAndType(WebElement webElement, String[] text) {
        clear(webElement);
        type(webElement, text);
    }

    public void clearAndType(WebElement webElement, double number) {
        clear(webElement);
        type(webElement, number);
    }

    public void pressEnter(WebElement webElement) {
        pressKeys(webElement, Keys.ENTER);
    }

    public void pressDelete(WebElement element, int noOfTimes) {
        for (int i = 0; i < noOfTimes; i++) {
            pressKeys(element, Keys.BACK_SPACE);
        }
    }

    public void pressKeys(WebElement element, CharSequence... keys) {
        waitHandler.waitForElementToBeClickable(element);
        element.sendKeys(keys);
       
    }

    /* Select/Deselect */
    public void select(WebElement webElement) {
        if (!isSelected(webElement)) {
            webElement.click();
        }
    }

    public void deselect(WebElement webElement) {
        if (isSelected(webElement)) {
            webElement.click();
        }
    }


    public void executeScript(String script) {
        ((JavascriptExecutor) driver).executeScript(script);
    }

    public Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    public Interactions scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    public Interactions removeTableRowVirtualization() {
        ((JavascriptExecutor) driver).executeScript("$.fn.visible = function () {return true};");
        return this;
    }

    public void scrollIntoViewAndClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(); arguments[0].click()", element);
    }

    public void scrollIntoViewRelativeAndClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: \"end\"}); arguments[0].click()", element);
    }

    public boolean isSelected(WebElement webElement) {
        return webElement.isSelected();
    }

    /* Enabled/Disabled */
    public boolean isEnabled(WebElement webElement) {
        return webElement.isEnabled();
    }

    public boolean isDisabled(WebElement webElement) {
        return !isEnabled(webElement);
    }

    /* get values*/

    public String getText(WebElement webElement) {
        waitHandler.waitForElementToBePresent(webElement);
        return webElement.getText();
    }

    public List<String> getText(List<WebElement> webElements) {
        List<String> listOfTexts = new ArrayList<>();
        for (WebElement webElement : webElements) {
            //waitHandler.waitForElementToDisplay(webElement);
            String text = webElement.getText();
            if(!text.equals(""))
                listOfTexts.add(text);
        }
        return listOfTexts;
    }

    public String getTextFieldValue(WebElement webElement) {
        return getAttribute(webElement, "value");
    }

    public String getAttribute(WebElement webElement, String attributeName) {
        return webElement.getAttribute(attributeName);
    }

    public Integer getIndex(List<WebElement> lisOfWebElements, String headerName) {
        return getText(lisOfWebElements).indexOf(headerName);
    }


    /* Js Methods*/

    public Interactions tabOut(WebElement element) {
        this.click(element).pressKeys(element, Keys.TAB);
        return this;
    }

    public String getCssValue(WebElement webElement, String propertyName) {
        waitHandler.waitForElementToDisplay(webElement);
        return webElement.getCssValue(propertyName);
    }

    public Interactions toggleCheckBox(WebElement element, Boolean selectionState) {
        Boolean checkBoxAlreadySelected = element.isSelected();
        if (checkBoxAlreadySelected != selectionState) {
            waitHandler.waitForElementToBePresent(element);
             // interaction.click() is not working in permission panel.
            element.click();
            //this.mouseClick(element);
        }
        return this;
    }

    public String formatDate(String utcDate) {
        if (utcDate == null) {
            return "-";
        }
        return (String) executeScript("return moment(new Date(arguments[0])).format('MMM D, YYYY | h:mm a')", utcDate);
    }

    public String getBorderColor (WebElement element) {
        return getCssValue(element, "border-bottom-color");
    }

    public String getBorderColorHexCode(WebElement element) {
        return getColorHexCode(getBorderColor(element));
    }

    public String getComputedBackgroundColor(WebElement element) {
        return executeScript("return window.getComputedStyle(arguments[0],':before').getPropertyValue('background-color')", element).toString();
    }

    public String getBackgroundColorHexCode(WebElement element) {
        return getColorHexCode(getComputedBackgroundColor(element));
    }

    private String getColorHexCode(String RgbColor) {
        String[] rgb = RgbColor.replace("rgba(", "").replace(")", "").replace("rgb(", "").split(",");
        Color c = new Color(Integer.valueOf(rgb[0].trim()), Integer.valueOf(rgb[1].trim()), Integer.valueOf(rgb[2].trim()));
        return "#"+ Integer.toHexString(c.getRGB() & 0xffffff);
    }

}
