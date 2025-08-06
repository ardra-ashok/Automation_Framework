package services;

import com.beust.jcommander.IDefaultProvider;
import org.apache.poi.ss.formula.atp.Switch;
import pages.ContactPage;
import pages.HomePage;

import java.util.Map;

public class toolShopServices {

    HomePage homePage;
    ContactPage contactPage;
    public toolShopServices(HomePage homePage, ContactPage contactPage){
        this.homePage = homePage;
        this.contactPage = contactPage;
    }

    public void verifyPage(String pageName) {
        switch(pageName.toLowerCase()){
            case "home":
                homePage.verifyHomePage();
                break;
            case "contact":
                contactPage.verifyContactPage();
                break;
            default:
                System.out.println("cannot find the option!!");

        }
    }

    public void navigateTo(String pageName) {
        switch(pageName.toLowerCase()){
            case "contact":
                homePage.navigateToPage(pageName);
                break;

        }

    }

    public void fillContactForm(Map<String, String> dataMap) throws Exception {
        contactPage.fillContactForm(dataMap);
    }

    public void clickOnSubmit() throws Exception {
        contactPage.clickOnSubmit();
    }

    public void verifyConfirmMessage(String successMsg) throws Exception {
        contactPage.verifySuccessMessage(successMsg);
    }
}
