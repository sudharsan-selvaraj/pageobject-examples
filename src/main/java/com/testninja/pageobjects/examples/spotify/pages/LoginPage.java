package com.testninja.pageobjects.examples.spotify.pages;

import com.testninja.pageobjects.wrappers.BasePage;
import com.testninja.pageobjects.wrappers.ScriptHelper;
import com.testninja.pageobjects.wrappers.annotations.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Page
public class LoginPage extends BasePage {

    @FindBy(css = "[ng-model=\"form.username\"]")
    private WebElement emailInput;

    @FindBy(css = "[ng-model=\"form.password\"]")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(ScriptHelper scriptHelper) {
        super(scriptHelper);
    }

    public void loginWithEmail(String email, String password) {
        interactions.clearAndType(emailInput, email)
                .clearAndType(passwordInput, password)
                .click(loginButton);
    }

}
