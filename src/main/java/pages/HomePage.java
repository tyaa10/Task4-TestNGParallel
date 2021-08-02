package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WebDriverSingletone;

public class HomePage {

    @FindBy(name = "search")
    private WebElement searchField;

    private WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        // WebDriver webDriver = WebDriverSingletone.getDriver();
        System.out.println("HomePage T: " + Thread.currentThread().getName());
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void searchByKeyword (final String keyword) { searchField.sendKeys(keyword, Keys.ENTER); }
}
