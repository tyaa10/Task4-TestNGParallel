package pages;

import decorator.custom.webelements.TextBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WebDriverSingletone;

public class CartPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='cart-receipt__sum-price']/span")
    private TextBlock price;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getOrderPriceTotal() {
        return Integer.parseInt(price.safeGetText(15));
    }
}

