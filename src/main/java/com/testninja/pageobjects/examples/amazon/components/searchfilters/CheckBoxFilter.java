package com.testninja.pageobjects.examples.amazon.components.searchfilters;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.testninja.pageobjects.wrappers.ScriptHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CheckBoxFilter extends BaseProductFilter {

    private boolean isOptionAnImage = false;

    public CheckBoxFilter(ScriptHelper scriptHelper,
                          String filterHeader) {
        super(scriptHelper, filterHeader);
    }

    public CheckBoxFilter(ScriptHelper scriptHelper,
                          String filterHeader,
                          boolean isOptionAnImage) {
        super(scriptHelper, filterHeader);
        this.isOptionAnImage = isOptionAnImage;
    }

    public void apply(List<String> optionsToSelect) {
        optionsToSelect
                .stream()
                .forEach(option -> {
                    waitForFilterToAppear();
                    clickSeeMore();
                    selectOption(getOptionCheckbox(option));
                });
    }

    public List<String> getSelectedOptions() {
        waitForFilterToAppear();
        clickSeeMore();

        return  getChildElement()
                .findElements(By.xpath(".//li/descendant::a[.//input[@type='checkbox'][@checked]]/descendant::span"))
                .stream()
                .map(interactions::getText)
                .filter(text -> !text.trim().equals(""))
                .collect(Collectors.toList());

    }

    private void selectOption(WebElement element) {
        if (!interactions.isSelected(element)) {
            interactions.javaScriptClick(element);
        }
    }

    private void deselectOption(WebElement element) {
        if (interactions.isSelected(element)) {
            interactions.click(element);
        }
    }

    private WebElement getOptionCheckbox(String label) {
        if (isOptionAnImage) {
            return getChildElement().findElement(By.xpath(".//li/descendant::a[.//span[aria-label='" + label + "']]/descendant::input[@type='checkbox']"));
        } else {
            return getChildElement().findElement(By.xpath(".//li/descendant::a[.//span[text()='" + label + "']]/descendant::input[@type='checkbox']"));
        }
    }
}
