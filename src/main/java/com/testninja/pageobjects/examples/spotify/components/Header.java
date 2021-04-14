package com.testninja.pageobjects.examples.spotify.components;

import com.testninja.pageobjects.wrappers.BaseWebComponent;
import com.testninja.pageobjects.wrappers.ScriptHelper;
import org.openqa.selenium.By;

public class Header extends BaseWebComponent {

    public void clickLogin() {
        interactions.click(findElement(getChildByTestId("login-button")));
    }
}
