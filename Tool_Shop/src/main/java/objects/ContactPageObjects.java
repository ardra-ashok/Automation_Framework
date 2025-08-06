package objects;

import org.openqa.selenium.By;

public class ContactPageObjects {
    public static final By contactPageTitle = By.tagName("h3");
    public static final By firstNameLabel = By.xpath("//label[.='First name']");
    public static final By firstNameInputField = By.xpath("//input[@id='first_name']");
    public static final By lastNameInputField = By.xpath("//input[@id='last_name']");
    public static final By emailInputField = By.id("email");
    public static final By messageInputField = By.cssSelector("#message");
    public static final By subjectSelect = By.cssSelector("#subject");
    public static final By chooseFileInput = By.cssSelector("#attachment");
    public static final By sendBtn = By.cssSelector("input[value='Send']");
    public static final By successMsgActual = By.cssSelector("div[role='alert']");
}
