package pages;

import decorator.custom.webelements.Button;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WebDriverSingletone;

import java.awt.*;

public class AddToCartPage extends AbstractPage {

    @FindBy(xpath = "//button[@class='buy-button button button_with_icon button_color_green button_size_large ng-star-inserted']")
    private Button buttonBuy;

    public AddToCartPage(WebDriver driver) {
        super(driver);
    }

    public SumCheckingPage pressButtonBuy() {
        buttonBuy.moveToAndSafeClick(15);
        return new SumCheckingPage(driver);
    }
}
