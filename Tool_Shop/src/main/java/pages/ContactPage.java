package pages;

import basePages.BasePage;
import core.WebDriverHandler;
import objects.ContactPageObjects;
import org.openqa.selenium.By;

public class ContactPage extends BasePage {


    private WebDriverHandler webDriverHandler;
    public ContactPage(WebDriverHandler webDriverHandler){
        this.webDriverHandler = webDriverHandler;
    }


    public void verifyContactPage() {
        isPageLoaded();
    }

    @Override
    public boolean isPageLoaded() {
        return webDriverHandler.isElementVisible(ContactPageObjects.contactPageTitle);
    }

    @Override
    public void navigate() {

    }
}
