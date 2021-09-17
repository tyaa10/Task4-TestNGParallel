package pages;

import decorator.custom.webelements.TextBlock;
import io.qameta.allure.Step;
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

    @FindBy(css = "a.cart-product__title")
    private TextBlock title;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get the total order value from the cart")
    public int getOrderPriceTotal() {
        return Integer.parseInt(price.safeGetText(15));
    }

    @Step("Get the product name from the cart")
    public String getTitle() {
        return title.safeGetText(15);
    }
}

