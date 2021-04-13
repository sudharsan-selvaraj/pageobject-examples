package com.testninja.pageobjects.examples.amazon.components.searchfilters;

import com.testninja.pageobjects.wrappers.ScriptHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PriceRangeFilter extends BaseProductFilter {

    public PriceRangeFilter(ScriptHelper scriptHelper) {
        super(scriptHelper, "Price");
    }

    public void apply(String lowPrice, String highPrice) {
        waitForFilterToAppear();
        interactions.clearAndType(getLowPriceInput(), lowPrice)
                .clearAndType(getHighPriceInput(), highPrice)
                .click(getSubmitButton());

    }

    private WebElement getLowPriceInput() {
        return getChildElement().findElement(By.id("low-price"));
    }

    private WebElement getHighPriceInput() {
        return getChildElement().findElement(By.id("high-price"));
    }

    private WebElement getSubmitButton() {
        return getChildElement().findElement(By.xpath(".//input[@type='submit']"));
    }
}
