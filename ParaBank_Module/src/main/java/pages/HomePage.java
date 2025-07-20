package pages;

import basePages.BasePage;
import core.WebDriverHandler;
import objects.HomePageObjects;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.Map;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class HomePage extends BasePage {

    private WebDriverHandler webDriverHandler;
    public HomePage(WebDriverHandler webDriverHandler){
        this.webDriverHandler = webDriverHandler;
    }

    public void navigateToRegister() {
        webDriverHandler.click(HomePageObjects.registerBtn);
        Assert.assertEquals(webDriverHandler.getText(HomePageObjects.registerPageTitle),HomePageObjects.registerPageExpectedTitle);
    }

    public void enterRegistrationDetails(Map<String, String> dataMap) throws Exception {
        webDriverHandler.waitForElementVisibility(HomePageObjects.firstNameTxt,5);
        webDriverHandler.enterData(HomePageObjects.firstNameTxt,dataMap.get("firstName"));
        webDriverHandler.enterData(HomePageObjects.lastNameTxt,dataMap.get("lastName"));

        WebElement addressInput = webDriverHandler.getElement(with(HomePageObjects.inputTag).below(HomePageObjects.lastNameTxt));
        webDriverHandler.enterData(addressInput,dataMap.get("address"));
        webDriverHandler.enterData(HomePageObjects.cityName,dataMap.get("city"));
        webDriverHandler.enterData(HomePageObjects.stateTxt,dataMap.get("state"));
        webDriverHandler.enterData(HomePageObjects.zipCodeTxt,dataMap.get("zipCode"));
        webDriverHandler.enterData(HomePageObjects.phoneTxt,dataMap.get("phoneNumber"));
        webDriverHandler.enterData(HomePageObjects.ssn,dataMap.get("ssn"));
        webDriverHandler.waitForElementVisibility(HomePageObjects.usernameTxt,2);
        webDriverHandler.enterData(HomePageObjects.usernameTxt,dataMap.get("username"));
        webDriverHandler.enterData(HomePageObjects.passwordTxt,dataMap.get("password"));
        WebElement confirmPassword = webDriverHandler.getElement(with(HomePageObjects.inputTag).below(HomePageObjects.passwordTxt));
        webDriverHandler.enterData(confirmPassword,dataMap.get("password"));
        webDriverHandler.click(HomePageObjects.registerSubmitBtn);

    }

    public void verifyAccountCreation(String successMsg) throws Exception {
        webDriverHandler.waitForElementVisibility(HomePageObjects.welcomeText,5);
        Assert.assertTrue(webDriverHandler.getText(HomePageObjects.welcomeText).toLowerCase().contains("welcome"));
        String registerSuccess = webDriverHandler.getElement(with(HomePageObjects.inputTagParagraph).below(HomePageObjects.welcomeText)).getText();
        Assert.assertEquals(registerSuccess,successMsg);
    }

    public void navigateToLogin() throws Exception {
        isPageLoaded();
        Assert.assertEquals(webDriverHandler.getText(HomePageObjects.loginPageTitle),HomePageObjects.loginPageExpectedTitle);
    }

    public void enterLoginDetails(Map<String, String> dataMap) throws Exception {
        webDriverHandler.enterData(HomePageObjects.usernameText,dataMap.get("username"));
        webDriverHandler.enterData(HomePageObjects.passwordField,dataMap.get("password"));
    }

    public void clickLogin(){
        webDriverHandler.click(HomePageObjects.loginBtn);
    }

    public void verifyRedirectToDashboard() throws Exception {
        webDriverHandler.waitForElementVisibility(HomePageObjects.loginSuccess,3);
        Assert.assertTrue(webDriverHandler.getURL().contains(HomePageObjects.dashboardEndpoint));
    }

    public void verifyLoginSuccess(String firstName) throws Exception {
        webDriverHandler.waitForElementVisibility(HomePageObjects.loginSuccess,3);
        Assert.assertEquals(webDriverHandler.getText(HomePageObjects.loginSuccess),HomePageObjects.loginSuccessExpectedTitle);
        System.out.println(webDriverHandler.getText(HomePageObjects.welcomeTextLogin));
        Assert.assertTrue(webDriverHandler.getText(HomePageObjects.welcomeTextLogin).contains(HomePageObjects.expectLoginWelcomeText+" "+firstName));
    }

    @Override
    public boolean isPageLoaded() {
        if (!webDriverHandler.isElementAvailable(HomePageObjects.loginPageTitle))
            System.out.println("Page not loaded");
        return true;
    }

    @Override
    public void navigate() {}

    public void verifyErrorMessage(String errorMsg) throws Exception {
        webDriverHandler.waitForElementVisibility(HomePageObjects.loginErrorMsg,3);
        Assert.assertEquals(webDriverHandler.getText(HomePageObjects.loginErrorMsg),errorMsg);
    }
}
