package objects;

import org.openqa.selenium.By;

public class HomePageObjects {

    public static final By pageTitle = By.cssSelector("a[class='navbar-brand']");
    public static By contactMenuNavItem = By.xpath("//a[@href='/contact']");
}
