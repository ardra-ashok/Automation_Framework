package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverHandler {

    public enum Browsers {IE, CHROME, FIREFOX, SAFARI, EDGE};

    private static WebDriver webDriver;
    private String baseUrl;

    private static Browsers browser = Browsers.CHROME;

    public void initializeDriver(){

    }

    public void waitForVisibilityOfElement(By by){
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));

    }




}
