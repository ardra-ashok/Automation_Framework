package com.automation.hooks;

import core.WebDriverHandler;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setUp() {
        WebDriverHandler.initializeDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
//        System.out.println(scenario.getName());
        if (WebDriverHandler.getDriver() != null) {
            WebDriverHandler.closeDriver();
        }

    }
}
