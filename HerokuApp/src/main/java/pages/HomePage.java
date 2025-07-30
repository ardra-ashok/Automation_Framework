package pages;

import basePages.BasePage;
import core.WebDriverHandler;
import objects.HomePageObjects;
import org.testng.Assert;

public class HomePage extends BasePage {

    private WebDriverHandler webDriverHandler;
    public HomePage(WebDriverHandler webDriverHandler){
        this.webDriverHandler = webDriverHandler;
    }

    @Override
    public boolean isPageLoaded() {
        webDriverHandler.waitForVisibilityOfElement(HomePageObjects.homePageTitle);
        if (!webDriverHandler.isElementAvailable(HomePageObjects.homePageTitle)) {
            System.out.println("Page not loaded");
            return false;
        }
        return true;
    }

    @Override
    public void navigate() {
        isPageLoaded();
    }

    public void navigateTo(String optionName) {
        switch (optionName.toLowerCase()) {
            case "ab-testing":
                webDriverHandler.click(HomePageObjects.abTestingLink);
                break;
            default:
                System.out.println("cannot find the correct option");
                break;
        }
    }

    public void verifyPageContent(String optionName) throws Exception {
        if(optionName.toLowerCase().contains("ab-testing")){
            webDriverHandler.waitForElementVisibility(HomePageObjects.abTestingTitle,2);
            Assert.assertTrue(webDriverHandler.getElement(HomePageObjects.abTestingTitle).getText().contains(HomePageObjects.abtestingExpectedTitle));
            Assert.assertTrue(webDriverHandler.getURL().contains(HomePageObjects.expectUrlAbTesting));
        }
    }
}
