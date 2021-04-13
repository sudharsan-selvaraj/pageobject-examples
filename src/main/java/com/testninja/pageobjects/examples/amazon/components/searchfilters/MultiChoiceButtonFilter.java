package com.testninja.pageobjects.examples.amazon.components.searchfilters;

import com.testninja.pageobjects.wrappers.ScriptHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class MultiChoiceButtonFilter extends BaseProductFilter {

    public MultiChoiceButtonFilter(ScriptHelper scriptHelper,
                                   String filterHeader) {
        super(scriptHelper, filterHeader);
    }

    public void apply(List<String> optionsToSelect) {
        optionsToSelect
                .stream()
                .forEach(option -> {
                    waitForFilterToAppear();
                    selectOption(getOptionButton(option));
                });
    }

    public List<String> getSelectedOptions() {
        waitForFilterToAppear();
        clickSeeMore();

        return  getChildElement()
                .findElements(By.xpath(".//li/descendant::*[contains(@class, 'a-button-selected')]"))
                .stream()
                .map(interactions::getText)
                .filter(text -> !text.trim().equals(""))
                .collect(Collectors.toList());

    }

    private void selectOption(WebElement element) {
        if (!interactions.isSelected(element)) {
            clickSeeMore();
            interactions.javaScriptClick(element);
        }
    }

    private void deselectOption(WebElement element) {
        if (interactions.isSelected(element)) {
            interactions.click(element);
        }
    }

    private WebElement getOptionButton(String label) {
        return getChildElement().findElement(By.xpath(".//li/descendant::button/span[.='"+ label +"']"));
    }

    private void isSelected() {

    }
}
