package com.testninja.pageobjects.examples.amazon.components.searchfilters;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.testninja.pageobjects.utils.Interactions;
import com.testninja.pageobjects.wrappers.BaseWebComponentFieldDecorator;
import com.testninja.pageobjects.wrappers.ScriptHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BaseProductFilter {

    protected Interactions interactions;
    protected WebDriver driver;
    private final String filterHeaderValue;

    private static final By seeMoreLink, clearLink;

    static {
        seeMoreLink = By.cssSelector("a.s-expander-text");
        clearLink = By.cssSelector(".s-navigation-clear-link");
    }

    public BaseProductFilter(ScriptHelper scriptHelper, String filterHeaderValue) {
        this.interactions = scriptHelper.getInteractions();
        this.driver = scriptHelper.getDriver();

        this.filterHeaderValue = filterHeaderValue;
        PageFactory.initElements(new BaseWebComponentFieldDecorator(scriptHelper), this);
    }

    public WebElement getChildElement() {
        WebElement filterContainer = getFilterContainer();
        return driver.findElement(By.cssSelector("[aria-labelledby='" + filterContainer.getAttribute("id") + "']"));
    }

    public boolean isSeeMoreLinkPresent() {
        return getChildElement().findElements(seeMoreLink).size() > 0;
    }

    public void clickSeeMore() {
        List<WebElement> link = getChildElement().findElements(seeMoreLink);
        if (link.size() > 0) {
            interactions.click(link.get(0));
        }
    }

    public boolean isClearLinkPresent() {
        return getChildElement().findElements(clearLink).size() > 0;
    }

    public void clickClear() {
        List<WebElement> clear = getChildElement().findElements(clearLink);
        if (clear.size() > 0) {
            interactions.click(clear.get(0));
        }
    }

    private By getFilterContainerBy() {
        return By.xpath(".//*[@id='s-refinements']/descendant::*[starts-with(@id, 'p_') and contains(@id, '-title') and contains(.,'" + filterHeaderValue + "')]");
    }

    public WebElement getFilterContainer() {
        return driver
                .findElement(getFilterContainerBy());
    }

    public void waitForFilterToAppear() {
        interactions.waitHandler.waitForElement(getFilterContainerBy());
        interactions.scrollIntoView(getFilterContainer());
    }
}
