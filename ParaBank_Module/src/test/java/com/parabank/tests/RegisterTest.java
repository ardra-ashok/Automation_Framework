package com.parabank.tests;

import configs.PropertyManager;
import core.WebDriverHandler;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegisterTest {

    private By paraLogo = By.xpath("//img[@title='ParaBank']");

    private WebDriverHandler webDriverHandler;
    protected PropertyManager propertyManager;
    @BeforeTest()
    public void setUp() throws Exception {
        PropertyManager.loadTestPropertiesFromYML("parabank");
    }

    @Test
    public void Register() throws Exception {


//        webDriverHandler.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        webDriverHandler.manage().window().fullscreen();
////        webDriverHandler.waitForVisibilityOfElement(paraLogo);
//
//        webDriver.get("https://parabank.parasoft.com/parabank/index.htm");
//        System.out.println(webDriver.getTitle());
//
//        webDriver.quit();

    }
}
