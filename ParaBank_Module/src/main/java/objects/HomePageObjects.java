package objects;

import org.openqa.selenium.By;

public class HomePageObjects {

//    Register section
    public static final By registerBtn = By.cssSelector("a[href*='register.htm']");
    public static final By registerPageTitle = By.cssSelector(".title");
    public static final String registerPageExpectedTitle = "Signing up is easy!";
    public static final By firstNameTxt  = By.id("customer.firstName");
    public static final By lastNameTxt = By.xpath("//input[@id='customer.lastName']");
    public static final By cityName  = By.name("customer.address.city");
    public static final By inputTag = By.tagName("input");
    public static final By stateTxt = By.xpath("//input[@id='customer.address.state']");
    public static final By zipCodeTxt = By.xpath("//input[@id='customer.address.zipCode']");
    public static final By phoneTxt = By.cssSelector("input[id='customer.phoneNumber']");
    public static final By ssn = By.xpath("//input[@id='customer.ssn']");
    public static final By usernameTxt = By.xpath("//input[@id='customer.username']");
    public static final By passwordTxt = By.xpath("//input[@id='customer.password']");
    public static final By registerSubmitBtn = By.xpath("//input[@value='Register']");

//    Verification section
    public static final By welcomeText = By.cssSelector("h1.title");
    public static final By inputTagParagraph = By.cssSelector("p");




}
