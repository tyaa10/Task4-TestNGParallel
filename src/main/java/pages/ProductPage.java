package pages;

import decorator.custom.webelements.Button;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends AbstractPage {

    @FindBy(xpath = "//button[@class='buy-button button button_with_icon button_color_green button_size_large ng-star-inserted']")
    private Button buttonBuy;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public CartPage pressButtonBuy() {
        buttonBuy.moveToAndSafeClick(15);
        return new CartPage(driver);
    }
}
