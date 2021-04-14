package com.testninja.pageobjects.examples.spotify.pages;

import com.testninja.pageobjects.examples.spotify.components.Header;
import com.testninja.pageobjects.examples.spotify.components.LeftNavBar;
import com.testninja.pageobjects.examples.spotify.components.Player;
import com.testninja.pageobjects.wrappers.BasePage;
import com.testninja.pageobjects.wrappers.ScriptHelper;
import com.testninja.pageobjects.wrappers.annotations.Page;
import com.testninja.pageobjects.wrappers.annotations.PageObject;
import org.openqa.selenium.support.FindBy;

@Page
public class SpotifyBasePage extends BasePage {

    @FindBy(css = ".Root__nav-bar")
    private LeftNavBar navBar;

    @FindBy(css = ".Root__top-bar")
    private Header header;

    @FindBy(css = ".Root__now-playing-bar")
    private Player player;

    @PageObject
    private LoginPage loginPage;

    public SpotifyBasePage(ScriptHelper scriptHelper) {
        super(scriptHelper);
    }

    public void loginWithCredentials(String email, String password) {
        header.clickLogin();
        loginPage.loginWithEmail(email, password);
        interactions.waitHandler.waitForElementToBePresent(header);
    }

    public Player getPlayer() {
        return player;
    }
}
