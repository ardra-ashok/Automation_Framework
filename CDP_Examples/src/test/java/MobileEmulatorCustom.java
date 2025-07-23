import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class MobileEmulatorCustom {

    @Test
    public void MobileTest() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver","/Users/aashok/IdeaProjects/Automation_Framework/supportData/drivers/chromedriver");
        ChromeDriver webDriver = new ChromeDriver();
        DevTools devTools = webDriver.getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap<>();
        deviceMetrics.put("width",600);
        deviceMetrics.put("height",600);
        deviceMetrics.put("deviceScaleFactor",50);
        deviceMetrics.put("mobile",true);

        webDriver.executeCdpCommand("Emulation.setDeviceMetricsOverride",deviceMetrics);
        webDriver.get("https://rahulshettyacademy.com/angularAppdemo/");
        webDriver.findElement(By.cssSelector(".navbar-toggler")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Library")).click();

        webDriver.quit();
    }
}
