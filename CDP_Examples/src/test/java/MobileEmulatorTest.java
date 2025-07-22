import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v123.emulation.Emulation;
import org.testng.annotations.Test;

import java.util.Optional;

public class MobileEmulatorTest {

    @Test
    public void testEmulator() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","/Users/aashok/IdeaProjects/Automation_Framework/supportData/drivers/chromedriver");
        ChromeDriver webDriver = new ChromeDriver();
        DevTools devTools = webDriver.getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setDeviceMetricsOverride(600, 1000, 50, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
        webDriver.get("https://rahulshettyacademy.com/angularAppdemo/");
        webDriver.findElement(By.cssSelector(".navbar-toggler")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Library")).click();

        webDriver.quit();

            System.out.println("hello");
    }
}
