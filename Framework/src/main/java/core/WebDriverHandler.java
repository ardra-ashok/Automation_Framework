package core;

import io.cucumber.java.an.E;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WebDriverHandler {



    public enum Browsers {IE, CHROME, FIREFOX, SAFARI, EDGE}

    private static WebDriver webDriver;
    private static WebDriverWait waitDriver;


    @Getter
    @Setter
    private static String baseUrl;


    public String getText(By by) {
       return getDriver().findElement(by).getText();
    }

    public void click(By by) {
        getDriver().findElement(by).click();
    }

    public void enterData(By by,String data) throws Exception {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getElement(by));
        getElement(by).sendKeys(data);
    }

    public void enterData(WebElement element,String data) throws Exception {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        element.sendKeys(data);
    }

    public WebElement getElement(By by) throws Exception {
        try {
            return getDriver().findElement(by);
        } catch (Exception ex) {
            throw new Exception("Element selector: " + by.toString(), ex);
        }
    }



    private static Browsers browser = Browsers.CHROME;

    public static WebDriver initializeDriver(){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);
            webDriver = new ChromeDriver(options);
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return webDriver;
    }

    public void waitForVisibilityOfElement(By by){
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));

    }

    public void navigateToUrl(String baseUrl) {
        webDriver.get(baseUrl);
    }

    public static void closeDriver() {
        if (webDriver != null)
            webDriver.quit();
        webDriver = null;
    }


    public static WebDriver getDriver() {
        if (webDriver == null) {
            try {
                webDriver = initializeDriver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return webDriver;
    }

    public boolean waitForElementVisibility(By by, int timeOutInSeconds) throws Exception {
        try {
             waitDriver = new WebDriverWait(getDriver(),Duration.ofSeconds(timeOutInSeconds));
             WebElement element = waitDriver.until(ExpectedConditions.visibilityOfElementLocated(by));
            if (element == null) {
                return false;
            }
            return true;
        } catch (Throwable t) {
            throw new Exception(String.format("Could not find element [%s] within [%d] seconds!", by.toString(), timeOutInSeconds));
        }
    }


}
