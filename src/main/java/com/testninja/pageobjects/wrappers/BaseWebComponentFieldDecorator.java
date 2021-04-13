package com.testninja.pageobjects.wrappers;

import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Field;

public class BaseWebComponentFieldDecorator implements FieldDecorator {

    private ScriptHelper scriptHelper;

    public BaseWebComponentFieldDecorator(ScriptHelper scriptHelper) {
        this.scriptHelper = scriptHelper;
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Object proxyWebElement = new WebDriverExtensionFieldDecorator(scriptHelper.getDriver()).decorate(loader, field);

        if(proxyWebElement instanceof BaseWebComponent) {
            ((BaseWebComponent)proxyWebElement).setScriptHelper(scriptHelper);
        }
        return proxyWebElement;
    }
}
