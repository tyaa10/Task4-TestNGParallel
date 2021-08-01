package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WebDriverSingletone;

public class AddToCartPage {

    @FindBy(xpath = "//button[@class='buy-button button button_with_icon button_color_green button_size_large ng-star-inserted']")
    private WebElement buttonBuy;

    private WebDriver webDriver;

    public AddToCartPage() {
        WebDriver webDriver = WebDriverSingletone.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public void pressButtonBuy (){
        buttonBuy.click();
    }
}
