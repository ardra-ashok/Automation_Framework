package services;

import pages.HomePage;

import java.util.Map;

public class ParaBankServices {

    HomePage homePage;
    public ParaBankServices(HomePage homePage){
        this.homePage = homePage;
    }

    public void navigateToRegister() {
        homePage.navigateToRegister();
    }

    public void enterRegistrationDetails(Map<String, String> dataMap) throws Exception {
        homePage.enterRegistrationDetails(dataMap);
    }

    public void verifyAccountCreation(String successMsg) throws Exception {
        homePage.verifyAccountCreation(successMsg);
    }
}
