package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WebDriverSingletone;

public class SumCheckingPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//div[@class='cart-receipt__sum-price']")
    private WebElement price;

    public SumCheckingPage(WebDriver webDriver) {
        // WebDriver webDriver = WebDriverSingletone.getDriver();
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public String getOrderPriceTotal() {
        WebDriverWait wait = new WebDriverWait(webDriver, 50);
        wait.until(ExpectedConditions.visibilityOf(price));
        return price.getText();
    }

    /* public boolean checkProductSum(int sum) {
        int result;
        String strPrice = price.getText();
        System.out.println();
        strPrice = strPrice.substring(0, strPrice.length() - 2);
        result = Integer.parseInt(strPrice);
        return result > sum;
    } */
}

