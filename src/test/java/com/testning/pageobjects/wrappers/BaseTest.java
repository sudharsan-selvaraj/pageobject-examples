package com.testning.pageobjects.wrappers;

import com.testninja.pageobjects.utils.Interactions;
import com.testninja.pageobjects.wrappers.PageObjectFactory;
import com.testninja.pageobjects.wrappers.ScriptHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;


import java.util.*;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;

    protected Interactions interactions;
    protected ScriptHelper scriptHelper;

    @BeforeSuite
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    protected void setup() {
        try {
            driver = new ChromeDriver();
            driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

            interactions = new Interactions(driver);
            scriptHelper = new ScriptHelper(driver, interactions);
            PageObjectFactory.init(this, Arrays.asList(ScriptHelper.class), Arrays.asList(scriptHelper));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    protected void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}
