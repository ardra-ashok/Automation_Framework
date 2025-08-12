import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class SauceMobileTest {

    public AndroidDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        BaseOptions options = new BaseOptions();
        options.setPlatformName("Android");
        options.setCapability("appium:platformVersion", "15.0");
        options.setCapability("appium:deviceName", "emulator-5554");
        options.setCapability("appium:automationName", "UiAutomator2");
        options.setCapability("appium:app", "/Users/aashok/IdeaProjects/Automation_Framework/supportData/apps/demoApp.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void testSuccessfulLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menuIV")));
        driver.findElement(By.id("menuIV")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@content-desc=\"Login Menu Item\"]")));
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Login Menu Item\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/nameET\"]")));
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/nameET\"]")).sendKeys("standard_user");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@resource-id='com.saucelabs.mydemoapp.android:id/passwordET']")));
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.saucelabs.mydemoapp.android:id/passwordET']")).sendKeys("secret_sauce");
        driver.findElement(By.id("loginBtn")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//androidx.recyclerview.widget.RecyclerView[@content-desc=\"Displays all products of catalog\"]/android.view.ViewGroup[1]")));
        Assert.assertTrue(driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@content-desc=\"Displays all products of catalog\"]/android.view.ViewGroup[1]")).isDisplayed(), "Products header not found after login.");
        System.out.println("Mobile login successful for standard_user!");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}