package pages;

import basePages.BasePage;
import core.WebDriverHandler;
import objects.ContactPageObjects;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.FileHandler;

import java.io.File;
import java.util.Map;

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

    public void fillContactForm(Map<String, String> dataMap) throws Exception {
        webDriverHandler.waitForElementVisibility(ContactPageObjects.firstNameLabel,2);
        webDriverHandler.enterData(ContactPageObjects.firstNameInputField,dataMap.get("firstName"));
        webDriverHandler.enterData(ContactPageObjects.lastNameInputField,dataMap.get("lastName"));
        webDriverHandler.enterData(ContactPageObjects.emailInputField,dataMap.get("email"));
        if(!dataMap.get("subject").isEmpty())
            webDriverHandler.select(ContactPageObjects.subjectSelect,dataMap.get("subject"));
        webDriverHandler.enterData(ContactPageObjects.messageInputField,dataMap.get("message"));
        if(dataMap.get("attachment").contains(".txt")) {
            File file = FileHandler.getOrCreateFile(dataMap.get("attachment"));
            webDriverHandler.enterData(ContactPageObjects.chooseFileInput, file.getAbsolutePath());
        }
    }

    public void clickOnSubmit() throws Exception {
        webDriverHandler.waitForElementVisibility(ContactPageObjects.sendBtn,2);
        webDriverHandler.click(ContactPageObjects.sendBtn);
    }

    public void verifySuccessMessage(String successMsg) throws Exception {
        webDriverHandler.fluentWait(ContactPageObjects.successMsgActual,5);
        String successMsgActual = webDriverHandler.getText(ContactPageObjects.successMsgActual);
        Assert.assertEquals(successMsgActual,successMsg);
    }

    public void verifyAlertMessage(String errMsgExpected) throws Exception {
        webDriverHandler.waitForElementVisibility(ContactPageObjects.alertMessage,2);
        String errMsgActual = webDriverHandler.getElement(ContactPageObjects.alertMessage).getText();
        Assert.assertEquals(errMsgExpected,errMsgActual,"Error Message is present");
    }
}
