package com.testninja.pageobjects.wrappers;

import com.testninja.pageobjects.utils.Interactions;
import org.openqa.selenium.WebDriver;

/**
 * Acts as a holder for commonly used objects across tests and page object classes
 * Instance of ScriptHelper will be passed as constructor arguments for page object classes;
 */

public class ScriptHelper {

    private Interactions interactions;
    private WebDriver driver;

    public ScriptHelper(WebDriver driver,
                        Interactions interactions) {
        this.driver = driver;
        this.interactions = interactions;
    }

    public WebDriver getDriver() {
        return driver;
    }


    public Interactions getInteractions() {
        return interactions;
    }
}
