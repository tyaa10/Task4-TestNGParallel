package pages;

import decorator.custom.webelements.TextBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WebDriverSingletone;

public class SumCheckingPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='cart-receipt__sum-price']/span")
    private TextBlock price;

    public SumCheckingPage(WebDriver driver) {
        super(driver);
    }

    public String getOrderPriceTotal() {
        return price.safeGetText(15);
    }
}

