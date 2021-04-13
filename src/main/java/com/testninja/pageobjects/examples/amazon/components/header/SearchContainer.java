package com.testninja.pageobjects.examples.amazon.components.header;

import com.testninja.pageobjects.wrappers.BaseWebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SearchContainer extends BaseWebComponent {

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchInput;

    @FindBy(id = "nav-search-dropdown-card")
    private WebElement categoryDropDown;


    public SearchContainer searchFor(String searchKeyword) {
        return searchFor(searchKeyword, null);
    }

    public SearchContainer searchFor(String searchKeyword, String category) {
        if (category != null) {
            selectCategoryFromDropdown(category);
        }

        if (searchKeyword != null) {
            enterSearchKeyword(searchKeyword, true);
        }
        return this;
    }

    public void pressSearch() {
        interactions.pressEnter(searchInput);
    }

    private void enterSearchKeyword(String keyword) {
        enterSearchKeyword(keyword, false);
    }

    private void enterSearchKeyword(String keyword, boolean pressEnter) {
        interactions.
                clear(searchInput)
                .type(searchInput, keyword);

        if(pressEnter) {
            pressSearch();
        }
    }

    private void selectCategoryFromDropdown(String category) {
        interactions.click(categoryDropDown);
        new Select(categoryDropDown.findElement(By.id("searchDropdownBox"))).selectByVisibleText(category);
    }

    public String getSelectedCategory() {
        return new Select(categoryDropDown.findElement(By.id("searchDropdownBox"))).getFirstSelectedOption().getText();
    }
}
