package com.automation.testngHeroku;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class herokuTest {

    WebDriver driver;
    WebDriverWait wait;
    @Test
    public void herokuApp() throws InterruptedException, IOException {
//        System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");


        setDriver(System.getProperty("browser",""));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://the-internet.herokuapp.com");
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        multipleWindows();

        driver.quit();
        
    }

    private void multipleWindows() throws InterruptedException {
        driver.findElement(By.linkText("Multiple Windows")).click();
        String parent=driver.getWindowHandle();
        driver.findElement(By.xpath("//a[.='Click Here']")).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> driver.getWindowHandles().size() > 1);
        Set<String> windowHandles = driver.getWindowHandles();
        for (String window : windowHandles) {

            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                wait.until(d -> !d.getTitle().isEmpty());
                System.out.println("New window title: " + driver.getTitle());
            }
        }

        driver.switchTo().window(parent);

    }

    private void setDriver(String browser) {
        if(browser.equalsIgnoreCase("chrome")) {
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.geolocation", 1);  // 1 = Allow, 2 = Block
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(options);
        }else {
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("geo.prompt.testing", true);
            profile.setPreference("geo.prompt.testing.allow", true);

            // Optionally disable the popup completely
            profile.setPreference("permissions.default.geo", 1); // 1 = allow, 2 = block

            FirefoxOptions options = new FirefoxOptions();
            options.setProfile(profile);

            driver = new FirefoxDriver(options);
        }
    }
}
