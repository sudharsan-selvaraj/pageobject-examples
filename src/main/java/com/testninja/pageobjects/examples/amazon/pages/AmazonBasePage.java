package com.testninja.pageobjects.examples.amazon.pages;

import com.testninja.pageobjects.examples.amazon.components.header.NavBar;
import com.testninja.pageobjects.wrappers.BasePage;
import com.testninja.pageobjects.wrappers.ScriptHelper;
import com.testninja.pageobjects.wrappers.annotations.Page;
import org.openqa.selenium.support.FindBy;

@Page
public class AmazonBasePage extends BasePage {

    @FindBy(id = "navbar")
    private NavBar navBar;

    public AmazonBasePage(ScriptHelper scriptHelper) {
        super(scriptHelper);
    }

    public void searchProduct(String productName) {
        searchProduct(productName, null);
    }

    public void searchProduct(String productName, String productCategory) {
        navBar.getSearchContainer()
                .searchFor(productName, productCategory);
    }

    public void changeCategory(String category) {
        if(category == null) {
            category = "All Categories";
        }
        navBar.getSearchContainer()
                .searchFor(null, category)
                .pressSearch();

    }

    public void resetCategory() {
        changeCategory(null);
    }
}
