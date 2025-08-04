package services;

import pages.HomePage;

import java.util.Map;

public class ParaBankServices {

    HomePage homePage;
    public ParaBankServices(HomePage homePage){
        this.homePage = homePage;
    }


    public void enterRegistrationDetails(Map<String, String> dataMap) throws Exception {
        homePage.enterRegistrationDetails(dataMap);
    }

    public void verifyAccountCreation(String successMsg) throws Exception {
        homePage.verifyAccountCreation(successMsg);
    }



    public void enterLoginDetails(Map<String, String> dataMap) throws Exception {
        homePage.enterLoginDetails(dataMap);
    }

    public void clickLogin() throws Exception {
        homePage.clickLogin();
    }

    public void verifyRedirectToDashboard() throws Exception {
        homePage.verifyRedirectToDashboard();
    }

    public void verifyLoginSuccess(String firstName) throws Exception {
        homePage.verifyLoginSuccess(firstName);
    }

    public void verifyErrorMessage(String errorMsg) throws Exception {
        homePage.verifyErrorMessage(errorMsg);
    }

    public void checkType(String expectedType) throws Exception {
        homePage.checkAttributeType(expectedType);
    }

    public void clickOnLink(String clickOnLink) throws InterruptedException {

        switch(clickOnLink){
            case "Forgot Password?":
                homePage.clickOnForgotPassword();
                break;
            default:
                System.out.println("cannot find the correct option");
                break;
        }
    }

    public void verifyNavigation(String pageName) {
        switch(pageName){
            case "forgotPassword":
                homePage.verifyForgotPassword();
                break;
            default:
                System.out.println("cannot find the correct option");
                break;
        }
    }

    public void navigateTo(String pageName) throws Exception {
        switch(pageName.toLowerCase()){
            case "home":
                homePage.navigate();
                break;
            case "login":
                homePage.navigateToLogin();
                break;
            case "register":
                homePage.navigateToRegister();
                break;
            default:
                System.out.println("cannot find the correct option");
                break;
        }
    }

    public void verifyLinkStatus() throws Exception {
        homePage.verifyLinksAreWorking();
    }
}
