package com.testninja.pageobjects.examples.spotify.components;

import com.testninja.pageobjects.wrappers.BaseWebComponent;
import org.openqa.selenium.By;

public class LeftNavBar extends BaseWebComponent {

    public void clickHome() {
        clickMenu("Home");
    }

    public void clickSearch() {
        clickMenu("Search");
    }

    public void clickYourLibrary() {
        clickMenu("Your Library");
    }

    private void clickMenu(String menuTitle) {
        interactions.click(findElement(By.linkText(menuTitle)));
    }
}
