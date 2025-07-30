package com.automation.testngHeroku;

import com.beust.ah.A;
import io.cucumber.java.en_old.Ac;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class testngRunner {

    WebDriver driver;
    WebDriverWait wait;
    @Test
    public void herokuApp() throws InterruptedException, IOException {
//        System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");


        setDriver(System.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://the-internet.herokuapp.com");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        testBrokenImage();
//        challengingDOM();
//        checkBoxes();
//        contextClick();
//        digestAuth();
//        disappearingElements();
//        dragAndDrop();
//        dropDown();
//        dynamicContent();
//        dynamicControls();
//        dynamicLoading();
//        entryAd();
//        exitIntent();
//        fileDownload();

//        fileUpload();
//        floatingMenu();
//        forgotPassword();
//        formAuthentication();
//        frames();
//        geoLocation();

//        horizontalSlider(3.5);
//        hoverOn();
//        infiniteScroll();

//        inputNumbers();
//        jQueryUI();
//        jsAlerts();
        keyPresses();
        driver.quit();
    }

    private void keyPresses() throws InterruptedException {
        driver.findElement(By.linkText("Key Presses")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h3"),"Key Presses"));

        WebElement target = driver.findElement(By.id("target"));
        WebElement result;

        target.click();
        target.sendKeys("A");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("result")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String resultText = (String) js.executeScript("return document.getElementById('result').innerText;");
        System.out.println("Captured with JS: " + resultText);

//        target.sendKeys(Keys.TAB);
//        System.out.println(result.getText().split(":")[1]);

        target = driver.findElement(By.id("target"));
        target.click();  // ✅ Important to trigger onkeyup listener
        target.sendKeys(Keys.ENTER);

        Thread.sleep(300);
        String text = (String) js.executeScript("return document.getElementById('result').textContent;");
        System.out.println("Pressed ENTER → " + text);



//        Keys[] keys = {Keys.ENTER, Keys.ESCAPE, Keys.ARROW_DOWN};

//        for (Keys key : keys) {
//            target = driver.findElement(By.id("target"));
//            target.sendKeys(key);
//            Thread.sleep(300);
//            String text = (String) js.executeScript("return document.getElementById('result').textContent;");
//            System.out.println("Pressed " + key.name() + " → " + text);
//        }
//

    }

    private void jsAlerts() throws InterruptedException {
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h3"),"JavaScript Alerts"));

        driver.findElement(By.cssSelector("button[onclick='jsAlert()']")).click();
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();

        driver.findElement(By.xpath("//button[.='Click for JS Confirm']")).click();
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().dismiss();

        driver.findElement(By.xpath("//button[.='Click for JS Confirm']")).click();
        driver.switchTo().alert().accept();

        driver.findElement(By.xpath("//button[.='Click for JS Prompt']")).click();
        Alert popup = driver.switchTo().alert();
        String text = "test string";
        for (char ch : text.toCharArray()) {
            popup.sendKeys(String.valueOf(ch));
            Thread.sleep(300); // small delay to mimic human typing
        }
        System.out.println(popup.getText());
        driver.switchTo().alert().accept();


    }

    private void jQueryUI() throws InterruptedException {


        driver.findElement(By.linkText("JQuery UI Menus")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h3"),"JQueryUI - Menu"));
        checkForEnabled();
        checkForDisabled();
        Download_script();
    }

    private void checkForDisabled() {
        WebElement element = driver.findElement(By.xpath("//a[.='Disabled']"));
        if(element.isSelected())
            System.out.println("enabled");
        else
            System.out.println("disabled");
    }



    public void Download_script() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//a[.='Enabled']"));
        Actions actions = new Actions(driver);


        // Hover over Enabled > Downloads > PDF
        WebElement enabledMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.='Enabled']")));
        actions.moveToElement(enabledMenu).perform();

        WebElement downloadsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.='Downloads']")));
        actions.moveToElement(downloadsMenu).perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.='Downloads']/following-sibling::ul")));
        List<WebElement> options = driver.findElements(By.xpath("//a[.='Downloads']/following-sibling::ul/li"));
        for(WebElement option :options)
            actions.moveToElement(option).click().perform();
    }

    private void checkForEnabled() {

        WebElement element = driver.findElement(By.xpath("//a[.='Enabled']"));
        if(element.isEnabled())
            System.out.println("enabled");
        else
            System.out.println("disabled");
    }

    private void inputNumbers() throws IOException, InterruptedException {
        driver.findElement(By.linkText("Inputs")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h3"),"Inputs"));
        WebElement numberInput = driver.findElement(By.xpath("//input[@type='number']"));
        Random rand = new Random();
        int value;
        for(int i=0;i<5;i++){
            value = rand.nextInt(80);
            numberInput.clear();
            numberInput.sendKeys(String.valueOf(value));
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            Files.copy(screenshot.toPath(), Paths.get("/Users/aashok/IdeaProjects/Automation_Framework/HerokuApp/target/screenshots/sc" + i + ".png"));
            numberInput.clear();
        }

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            numberInput.sendKeys(Keys.ARROW_UP);
        }

        Thread.sleep(1000);

        numberInput.clear();

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            numberInput.sendKeys(Keys.ARROW_DOWN);
        }

    }

    public void takeScreenshot(){

    }

    private void infiniteScroll() throws InterruptedException {
        driver.findElement(By.linkText("Infinite Scroll")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div[class='example'] h3"),"Infinite Scroll"));
        Actions actions = new Actions(driver);
        for(int i=0;i<50;i++){
            actions.sendKeys(Keys.PAGE_DOWN).build().perform();
            Thread.sleep(1000);
        }


    }

    private void hoverOn() throws InterruptedException {

        driver.findElement(By.linkText("Hovers")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div[class='example'] h3"),"Hovers"));
        List<WebElement> images = driver.findElements(By.cssSelector("div[class='figure']"));
        Actions actions = new Actions(driver);
        for(WebElement img: images){
            actions.moveToElement(img).perform();
            Thread.sleep(1000);
            WebElement caption = img.findElement(By.className("figcaption"));
            System.out.println("Caption: " + caption.getText());
        }
    }

    private void horizontalSlider(double value) {

        driver.findElement(By.linkText("Horizontal Slider")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div[class='example'] h3"),"Horizontal Slider"));
        WebElement slider = driver.findElement(By.cssSelector("input[type='range']"));
        int steps = (int) (value/0.5);
        for(int i=0;i<steps;i++){
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
        Assert.assertEquals(driver.findElement(By.id("range")).getText(),String.valueOf(value));
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

    private void geoLocation() throws InterruptedException {

        driver.findElement(By.linkText("Geolocation")).click();
        driver.findElement(By.xpath("//button[.='Where am I?']")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div[class='example'] h3"),"Geolocation"));
        System.out.println(driver.findElement(By.cssSelector("p#demo")).getText().trim());
        Thread.sleep(3000);
    }

    private void frames() {

        driver.findElement(By.linkText("Frames")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div[class='example'] h3"),"Frames"));
        driver.findElement(By.linkText("Nested Frames")).click();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        System.out.println(driver.findElement(By.tagName("body")).getText());
        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-middle");
        System.out.println(driver.findElement(By.tagName("body")).getText());
        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-right");
        System.out.println(driver.findElement(By.tagName("body")).getText());
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-bottom");
        System.out.println(driver.findElement(By.tagName("body")).getText());
        driver.switchTo().defaultContent();
        driver.navigate().back();
        driver.findElement(By.linkText("iFrame")).click();
        driver.findElement(By.xpath("//button[@class='tox-notification__dismiss tox-button tox-button--naked tox-button--icon']")).click();
    }

    private void formAuthentication() {

        driver.findElement(By.linkText("Form Authentication")).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='username']"))));
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

    }

    private void forgotPassword() {
        driver.findElement(By.linkText("Forgot Password")).click();
        WebElement email_field = driver.findElement(By.id("email"));
        email_field.sendKeys("test@gmail.com");
        driver.findElement(By.xpath("//i[.='Retrieve password']")).click();
    }

    private void floatingMenu() {
        driver.findElement(By.linkText("Floating Menu")).click();
        WebElement navBar = driver.findElement(By.id("menu"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,250)");
        if (navBar.getCssValue("absolute").equals("fixed")) {
            System.out.println("Navbar is floating.");
        } else {
            System.out.println("Navbar is not floating.");
        }

    }

    private void fileUpload() throws InterruptedException {
        driver.findElement(By.linkText("File Upload")).click();
        File file = new File("/Users/aashok/IdeaProjects/Automation_Framework/supportData/drivers/chromedriver");
        driver.findElement(By.xpath("//input[@id='file-upload']")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.xpath("//input[@id='file-submit']")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h3[.='File Uploaded!']"),"File Uploaded!"));


    }

    private void fileDownload() {
        driver.findElement(By.linkText("File Download")).click();
        List<WebElement> links = driver.findElements(By.cssSelector("#content a"));
        for(WebElement link:links)
            link.click();

    }

    private void exitIntent() throws InterruptedException {
        driver.findElement(By.linkText("Exit Intent")).click();
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.moveByOffset(100, 100).perform();
        Thread.sleep(1000);
        actions.moveByOffset(0, -100).perform();
        Thread.sleep(1000);
        actions.moveByOffset(200, 500).perform();
        actions.moveByOffset(600, 900).perform();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal")));
            driver.findElement(By.xpath("//p[.='Close']")).click();
        }catch (NoSuchElementException e){
            System.out.println("Modal did not appear.");
        }

    }

    private void entryAd() throws InterruptedException {
        driver.findElement(By.linkText("Entry Ad")).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal")));
        driver.findElement(By.xpath("//p[.='Close']")).click();
    }

    private void dynamicLoading() {
        driver.findElement(By.linkText("Dynamic Loading")).click();
        driver.findElement(By.xpath("//a[.='Example 1: Element on page that is hidden']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[id='start'] button"))).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div[id='finish'] h4"),"Hello World!"));
        driver.navigate().back();
        driver.findElement(By.xpath("//a[.='Example 2: Element rendered after the fact']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[id='start'] button"))).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div[id='finish'] h4"),"Hello World!"));

    }

    private void dynamicControls() {
        driver.findElement(By.linkText("Dynamic Controls")).click();
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//button[.='Remove']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[.='Add']")));
        driver.findElement(By.xpath("//button[.='Add']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[.='Remove']")));

        driver.findElement(By.xpath("//button[.='Enable']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[.='Disable']")));
        WebElement text_field = driver.findElement(By.xpath("//input[@type='text']"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@id='message']")));

        wait.until(ExpectedConditions.elementToBeClickable(text_field)).sendKeys("Check");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[.='Disable']"))).click();
    }

    private void dynamicContent() throws InterruptedException {
        driver.findElement(By.linkText("Dynamic Content")).click();
        WebElement element = driver.findElement(By.linkText("click here"));
        element.click();

        String firstText = driver.findElement(By.cssSelector(".large-10.columns")).getText();
        Thread.sleep(1000);
        element = driver.findElement(By.linkText("click here"));
        element.click();
        String newText = driver.findElement(By.cssSelector(".large-10.columns")).getText();

        if(!newText.equals(firstText))
            System.out.println("content is dynamic");
        else
            System.out.println("content is same");



    }

    private void dropDown() throws InterruptedException {
        driver.navigate().to("https://the-internet.herokuapp.com/dropdown");
        WebElement dropDown = driver.findElement(By.id("dropdown"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdown")));
        Select select = new Select(dropDown);
        select.selectByIndex(1);
        Thread.sleep(1000);
        select.selectByValue("2");
        Thread.sleep(1000);
        select.selectByVisibleText("Option 1");
        Thread.sleep(1000);
    }

    private void dragAndDrop() throws InterruptedException, IOException {
        driver.navigate().to("https://the-internet.herokuapp.com/drag_and_drop");
        String script = """
            function simulateDragDrop(source, target) {
                const dataTransfer = new DataTransfer();
                const dragStartEvent = new DragEvent('dragstart', { dataTransfer });
                source.dispatchEvent(dragStartEvent);

                const dropEvent = new DragEvent('drop', { dataTransfer });
                target.dispatchEvent(dropEvent);

                const dragEndEvent = new DragEvent('dragend', { dataTransfer });
                source.dispatchEvent(dragEndEvent);
            }
            const source = document.getElementById('column-a');
            const target = document.getElementById('column-b');
            simulateDragDrop(source, target);
        """;

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script);

        Thread.sleep(2000);


    }

    private void disappearingElements() {
        driver.navigate().to("https://the-internet.herokuapp.com/disappearing_elements");

        int maxtry = 10;
        boolean galleryFound = false;
        for(int i=0;i<=maxtry;i++){
            List<WebElement> listItems = driver.findElements(By.cssSelector("ul li a"));
            for(WebElement item: listItems){
                String itemtext = item.getText();
                if(itemtext.toLowerCase().contains("gallery")){
                    System.out.println("gallery found");
                    item.click();
                    galleryFound = true;
                }
            }
            if(galleryFound)
                break;
            driver.navigate().refresh();
            System.out.println("gallery not found");
        }
    }

    private void digestAuth() {
        driver.navigate().to("https://admin:admin@the-internet.herokuapp.com/digest_auth");
        WebElement title = driver.findElement(By.xpath("//div[@class='example']/h3"));
        WebElement successMsg = driver.findElement(RelativeLocator.with(By.tagName("p")).below(title));

        Assert.assertTrue(successMsg.getText().contains("Congratulations!"));

    }

    private void contextClick() throws InterruptedException {

        driver.findElement(By.linkText("Context Menu")).click();
        Thread.sleep(1000);
        WebElement contextClick = driver.findElement(By.xpath("//div[@id='hot-spot']"));
        Actions act = new Actions(driver);
        act.moveToElement(contextClick).perform();
        act.contextClick().perform();

        Alert popup = driver.switchTo().alert();
        popup.accept();

    }

    private void checkBoxes() throws InterruptedException {
        driver.findElement(By.linkText("Checkboxes")).click();

        Thread.sleep(2000);
       List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for(WebElement checkBox: checkBoxes){
            checkBox.click();
        }
    }

    private void challengingDOM() throws InterruptedException {
        driver.findElement(By.linkText("Challenging DOM")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[class='button']")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("a[class='button alert']")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("a[class='button success']")).click();



    }

    private void testBrokenImage() throws InterruptedException {

        driver.findElement(By.cssSelector("a[href='/broken_images']")).click();
        Thread.sleep(1000);
        List<WebElement> imageList = driver.findElements(By.tagName("img"));
        for(WebElement img:imageList){
            String imageUrl = img.getAttribute("src");


            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(imageUrl).openConnection();
                conn.setConnectTimeout(1000);
                conn.setRequestMethod("GET");
                conn.connect();
                int responseCode = conn.getResponseCode();
                if(responseCode!=200)
                    System.out.println("Broken image");
                else
                    System.out.println("Valid image");
            } catch (IOException e) {
                System.out.println("Error checking image");
            }
        }
    }
}
