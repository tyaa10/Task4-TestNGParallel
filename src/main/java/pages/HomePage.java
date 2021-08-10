package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseSearchablePage {
    @FindBy(tagName = "app-slider")
    WebElement slider;
    public HomePage(WebDriver driver) throws InterruptedException {
        super(driver);
        Thread.sleep(3);
        System.out.println(slider.getText());
    }
}
