package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WebDriverSingletone;

public class SumCheckingPage {

    @FindBy(xpath = "//div[@class='cart-receipt__sum-price']")
    private WebElement price;

    public SumCheckingPage() {
        WebDriver webDriver = WebDriverSingletone.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public boolean checkProductSum(int sum) {

        int result;
        String strPrice = price.getText();
        strPrice = strPrice.substring(0, strPrice.length() - 2);
        result = Integer.parseInt(strPrice);

        if (result > 50000) {
            return true;
        } else
            return false;
    }
}

