package com.automation.testngHeroku;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;

public class test {


    WebDriver webDriver;

    @Test
    public void testEmailPasswordFieldVisible() {
        webDriver = new ChromeDriver();
        webDriver.get("https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_login_page/9a83bda125cd7398f9f482a3d6d45ea4/static/attachments/reference_page.html");
        WebElement emailInput = webDriver.findElement(By.xpath("//input[@type='email']"));

        AssertJUnit.assertEquals(emailInput.getAttribute("id"),"email-input");
        WebElement passwordInput = webDriver.findElement(By.xpath("//input[@type='password']"));
        Assert.assertEquals(passwordInput.getAttribute("id"),"password-input");
        WebElement loginButton = webDriver.findElement(By.xpath("//button[.='Login']"));
        Assert.assertEquals(loginButton.getAttribute("id"),"login-button");
    }

    // Check if the valid credentials work
//    @Test
//    public void checkValidLogin() {
//        WebElement emailInput = webDriver.findElement(By.xpath("//input[@type='email']"));
//        WebElement passwordInput = webDriver.findElement(By.xpath("//input[@type='password']"));
//        WebElement loginButton = webDriver.findElement(By.xpath("//button[.='Login']"));
//        emailInput.clear();
//        passwordInput.clear();
//        emailInput.sendKeys("login@codility.com");
//        passwordInput.sendKeys("password");
//        loginButton.click();
//        WebElement successMessage = webDriver.findElement(By.cssSelector(".message.success"));
//        Assert.assertTrue(successMessage.isDisplayed());
//        // or if(successMessage.isDisplayed())
//        Assert.assertTrue(successMessage.getText().contains("Welcome to Codility"));
//    }
//
//    // check if the wrong credentials work
//    @Test
//    public void checkInvalidLogin() {
//        WebElement emailInput = webDriver.findElement(By.xpath("//input[@type='email']"));
//        WebElement passwordInput = webDriver.findElement(By.xpath("//input[@type='password']"));
//        WebElement loginButton = webDriver.findElement(By.xpath("//button[.='Login']"));
//        emailInput.clear();
//        passwordInput.clear();
//        emailInput.sendKeys("unknown@codility.com");
//        passwordInput.sendKeys("password");
//        loginButton.click();
//        WebElement errorMessage = webDriver.findElement(By.cssSelector(".message.error"));
//        Assert.assertTrue(errorMessage.isDisplayed());
//        // or if(errorMessage.isDisplayed())
//        Assert.assertTrue(errorMessage.getText().contains("You shall not pass! Arr!"));
//    }
//
//    // check email validation is working
//    @Test
//    public void emailValidation() {
//        WebElement emailInput = webDriver.findElement(By.xpath("//input[@type='email']"));
//        WebElement passwordInput = webDriver.findElement(By.xpath("//input[@type='password']"));
//        WebElement loginButton = webDriver.findElement(By.xpath("//button[.='Login']"));
//        emailInput.clear();
//        passwordInput.clear();
//        emailInput.sendKeys("abc");
//        passwordInput.sendKeys("password");
//        loginButton.click();
//        WebElement emailInvalidMsg = webDriver.findElement(By.xpath("//div[@class='validation error']"));
//        if(emailInvalidMsg.isDisplayed())
//            Assert.assertTrue(emailInvalidMsg.getText().contains("Enter a valid email"));
//    }
//
//    // check for empty credentials
//    @Test
//    public void checkForEmptyFields() {
//        WebElement emailInput = webDriver.findElement(By.xpath("//input[@type='email']"));
//        WebElement passwordInput = webDriver.findElement(By.xpath("//input[@type='password']"));
//        WebElement loginButton = webDriver.findElement(By.xpath("//button[.='Login']"));
//        // - email empty
//        emailInput.clear();
//        passwordInput.clear();
//        emailInput.sendKeys("");
//        passwordInput.sendKeys("password");
//        loginButton.click();
//        WebElement emailEmptyMsg = driver.findElement(By.xpath("//div[@class='validation error']"));
//        if(emailEmptyMsg.isDisplayed())
//            Assert.assertTrue(emailEmptyMsg.getText().contains("Email is required"));
//        // -password empty
//        emailInput.clear();
//        passwordInput.clear();
//        emailInput.sendKeys("login@codility.com");
//        passwordInput.sendKeys("");
//        loginButton.click();
//        WebElement passwordEmptyMsg = driver.findElement(By.xpath("//div[@class='validation error']"));
//        if(passwordEmptyMsg.isDisplayed())
//            Assert.assertTrue(passwordEmptyMsg.getText().contains("Password is required"));
//    }
//
//    // check tab and enter keys are working
//    @Test
//    public void checkKeysWorking() {
//        WebElement emailInput = webDriver.findElement(By.xpath("//input[@type='email']"));
//        WebElement passwordInput = webDriver.findElement(By.xpath("//input[@type='password']"));
//        WebElement loginButton = webDriver.findElement(By.xpath("//button[.='Login']"));
//        emailInput.clear();
//        passwordInput.clear();
//        emailInput.sendKeys("login@codility.com");
//        emailInput.sendKeys(Keys.TAB);
//        passwordInput.sendKeys("password");
//        loginButton.sendKeys(keys.ENTER);
//    }



}
