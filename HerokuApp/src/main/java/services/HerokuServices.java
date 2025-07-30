package services;

import pages.HomePage;

public class HerokuServices  {

    HomePage homePage;
    public HerokuServices(HomePage homePage){
        this.homePage=homePage;
    }

    public void navigateTo(String pageName) {
        switch(pageName.toLowerCase()){
            case "home":
                homePage.navigate();
                break;
            case "ab-testing":
                homePage.navigateTo(pageName);
                break;
            default:
                System.out.println("cannot find the correct option");
                break;
        }
    }

    public void verifyPageContent(String pageName) throws Exception {
        homePage.verifyPageContent(pageName);
    }
}
