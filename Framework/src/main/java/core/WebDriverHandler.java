package core;

import configs.CoreParams;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverHandler {

    public enum Browsers {IE, CHROME, FIREFOX, SAFARI, EDGE}
    private static Browsers browser = Browsers.CHROME;

    private static WebDriver webDriver;
    private static WebDriverWait waitDriver;


    @Setter
    private static int implicitWaitInSecs = 10;
    private static int explicitWaitInSecs = 10;


    @Getter
    @Setter
    private static String baseUrl;

    public static void setBrowser(Browsers browser) {
        WebDriverHandler.browser = browser;
    }

    public String getTitle() {
        return getDriver().getTitle();
    }

    public String getURL() {
        return getDriver().getCurrentUrl();
    }
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

    public List<WebElement> getElements(By by) throws Exception {
        try {
            return getDriver().findElements(by);
        } catch (Exception ex) {
            throw new Exception("Element selector: " + by.toString(), ex);
        }
    }

    public static WebDriver initializeDriver() throws Exception {
        try {
            switch (browser) {
                case FIREFOX:
                    webDriver = new FirefoxDriver(getFireFoxOptions());
                    break;
                case SAFARI:
                    webDriver =  new SafariDriver(getSafariOptions());
                    break;
                case EDGE:
                    webDriver = new EdgeDriver(getEdgeOptions());
                    webDriver.manage().window().maximize();
                    break;
                default:
                    webDriver = new ChromeDriver(getChromeOptions());
                    break;
            }
            return webDriver;
        } catch (Exception ex) {
            throw new Exception("Failed to initialize web driver", ex);
        }
    }

    private static EdgeOptions getEdgeOptions() {
        System.setProperty("webdriver.edge.driver", CoreParams.DRIVERS_DIR + "/msedgedriver");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--window-size=970,1080");
//        options.addArguments("--headless");
        return options;
    }

    private static ChromeOptions getChromeOptions() {
        System.setProperty("webdriver.chrome.driver", CoreParams.DRIVERS_DIR+"/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        return options;
    }

    private static FirefoxOptions getFireFoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
//        options.addArguments("--headless");
        return options;
    }

    private static SafariOptions getSafariOptions() {
        SafariOptions options = new SafariOptions();
        options.setUseTechnologyPreview(true);
        return new SafariOptions();
    }


    public void waitForVisibilityOfElement(By by){
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));

    }

    public void navigateToUrl(String baseUrl) {
        webDriver.get(baseUrl);
    }

    public static void closeDriver() {
        if (webDriver != null) {
                webDriver.quit();
            webDriver = null;
        }
    }

    public static WebDriver getDriver() {
        if (webDriver == null) {
            try {
                if (System.getProperties().contains("timeout") && System.getProperty("timeout").toString() != null) {
                    implicitWaitInSecs = Integer.parseInt(System.getProperty("timeout"));
                }
                webDriver = initializeDriver();
                webDriver.manage().timeouts().implicitlyWait(implicitWaitInSecs, TimeUnit.SECONDS);
                webDriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.MINUTES);
                webDriver.manage().timeouts().setScriptTimeout(15,TimeUnit.MINUTES);

            } catch (Exception e) {
                e.printStackTrace();
            }
            waitDriver = new WebDriverWait(webDriver, Duration.ofSeconds(explicitWaitInSecs));
        }
        webDriver.manage().window().maximize();
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

    public boolean isElementAvailable(By by) {
        try {
            WebElement element = getDriver().findElement(by);
            if (element != null)
                return true;
            return false;
        } catch (Throwable t) {
            return false;
        }
    }

    public boolean isElementVisible(By by) {
        try {
            WebElement element = getElement(by);
            if ((element != null) && (element.isDisplayed()))
                return true;
            return false;
        } catch (Throwable t) {
            return false;
        }
    }

    public void waitThreadForDuration(int timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void waitForDuration(int timeInSeconds) {
        waitDriver.withTimeout(Duration.ofSeconds(timeInSeconds));
    }


    public static File takeScreenshotFile(String scenarioName, String runTimestamp) {
        WebDriver currentDriver = getDriver();
        if (currentDriver == null) return null;

        File srcFile = ((TakesScreenshot) currentDriver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
        String safeScenarioName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_");
        String screenshotDir = "target/HTML Reports/" + safeScenarioName + "/" + runTimestamp + "/screenshots/";
        String screenshotFileName = safeScenarioName + "_" + timestamp + ".png";
        String fullScreenshotPath = screenshotDir + screenshotFileName;

        try {
            File destFile = new File(fullScreenshotPath);
            destFile.getParentFile().mkdirs(); // Ensure directories exist
            Files.copy(srcFile.toPath(), destFile.toPath());
            return destFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



}
