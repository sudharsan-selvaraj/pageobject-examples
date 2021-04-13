package com.testninja.pageobjects.examples.amazon.components.header;

import com.testninja.pageobjects.wrappers.BaseWebComponent;
import org.openqa.selenium.support.FindBy;

public class NavBar extends BaseWebComponent {

    @FindBy(css = "#nav-search")
    private SearchContainer searchContainer;


    public SearchContainer getSearchContainer() {
        return searchContainer;
    }
}
