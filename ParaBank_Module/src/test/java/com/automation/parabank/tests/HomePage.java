package com.automation.parabank.tests;

import configs.PropertyManager;
import core.WebDriverHandler;
import io.cucumber.java.*;

import org.openqa.selenium.By;


public class HomePage {
    public static void navigateToRegister() {

    }

//    private By paraLogo = By.xpath("//img[@title='ParaBank']");
//    private WebDriverHandler webDriverHandler;
//    protected PropertyManager propertyManager;
//
//    public HomePage(WebDriverHandler webDriverHandler){
//        this.webDriverHandler = webDriverHandler;
//    }
//    public HomePage(){}
//
//    @Before
//    public void setUp() throws Exception {
//
//        try {
//            PropertyManager.loadTestPropertiesFromYML("parabank");
//        } catch (Exception e) {
//            e.printStackTrace(); // or throw new RuntimeException(e);
//        }
//
//        String url = PropertyManager.getEnvironmentVariable("parabank.url");
//        webDriverHandler.setBaseUrl(url);
//
////        webDriverHandler.setBrowser(WebDriverHandler.Browsers.valueOf(browserStr.toUpperCase()));
//    }
//
//
//    public void Register()  {
//
////        webDriverHandler.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
////        webDriverHandler.manage().window().fullscreen();
//////        webDriverHandler.waitForVisibilityOfElement(paraLogo);
////
////        webDriver.get("https://parabank.parasoft.com/parabank/index.htm");
////        System.out.println(webDriver.getTitle());
////
////        webDriver.quit();

//    }
}
