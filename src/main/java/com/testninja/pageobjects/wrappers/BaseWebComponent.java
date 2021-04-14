package com.testninja.pageobjects.wrappers;

import com.github.webdriverextensions.WebComponent;
import com.testninja.pageobjects.utils.Interactions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseWebComponent extends WebComponent {

    protected ScriptHelper scriptHelper;
    protected Interactions interactions;

    public BaseWebComponent() {
        super();
    }

    public BaseWebComponent(ScriptHelper scriptHelper) {
        super();
        setScriptHelper(scriptHelper);
    }

    public void setScriptHelper(ScriptHelper scriptHelper) {
        this.scriptHelper = scriptHelper;
        this.interactions = scriptHelper.getInteractions();
        PageFactory.initElements(new BaseWebComponentFieldDecorator(scriptHelper), this);
    }

    public WebDriver getDriver() {
        return scriptHelper.getDriver();
    }

    protected static By getChildByTestId(String testId) {
        return By.cssSelector("[data-testid='" + testId + "']");
    }

}
