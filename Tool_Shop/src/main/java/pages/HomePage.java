package pages;

import basePages.BasePage;
import core.WebDriverHandler;
import objects.HomePageObjects;


public class HomePage extends BasePage {

    private WebDriverHandler webDriverHandler;
    public HomePage(WebDriverHandler webDriverHandler){
        this.webDriverHandler = webDriverHandler;
    }

    @Override
    public boolean isPageLoaded() {
        return webDriverHandler.isElementVisible(HomePageObjects.pageTitle);

    }

    @Override
    public void navigate() {
    }

    public void verifyHomePage() {
        isPageLoaded();
    }

    public void navigateToPage(String pageName) {
        switch(pageName.toLowerCase()){
            case "contact":
                webDriverHandler.click(HomePageObjects.contactMenuNavItem);
                break;

        }
    }

}
