package objects;

import org.openqa.selenium.By;

public class HomePageObjects {

    public static final By homePageTitle = By.cssSelector(".heading");
    public static final By abTestingLink = By.cssSelector("a[href='/abtest']");
    public static final By abTestingTitle = By.cssSelector("div[class='example'] h3");
    public static final String abtestingExpectedTitle = "A/B Test Control";
    public static final String expectUrlAbTesting = "/abtest";
}
