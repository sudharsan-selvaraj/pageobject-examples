package com.testninja.pageobjects.wrappers;

import com.testninja.pageobjects.utils.Interactions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;

public class BasePage {

    protected Interactions interactions;
    protected WebDriver driver;
    protected ScriptHelper scriptHelper;

    public BasePage(ScriptHelper scriptHelper) {
        this.scriptHelper = scriptHelper;
        this.driver = scriptHelper.getDriver();
        this.interactions = scriptHelper.getInteractions();

        PageFactory.initElements(new BaseWebComponentFieldDecorator(scriptHelper), this);
        PageObjectFactory.init(this, Arrays.asList(new Class[]{ScriptHelper.class}), Arrays.asList(new Object[]{scriptHelper}));
    }
}
