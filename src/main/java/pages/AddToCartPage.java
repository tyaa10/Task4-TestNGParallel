package pages;

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

public class AddToCartPage {

    @FindBy(xpath = "//button[@class='buy-button button button_with_icon button_color_green button_size_large ng-star-inserted']")
    private WebElement buttonBuy;

    private WebDriver webDriver;

    public AddToCartPage(WebDriver webDriver) {
        // WebDriver webDriver = WebDriverSingletone.getDriver();
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public SumCheckingPage pressButtonBuy() {
        /* try {
            Robot robot = new Robot();
            robot.mouseMove(0,0);
        } catch (AWTException ignored) {}
        WebDriverWait wait = new WebDriverWait(webDriver, 50);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", buttonBuy); */
        Actions actions = new Actions(webDriver);
        actions.moveToElement(buttonBuy).perform();
        WebDriverWait wait = new WebDriverWait(webDriver, 50);
        wait.until(ExpectedConditions.visibilityOf(buttonBuy));
        buttonBuy.click();
        wait.until(
            driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        return new SumCheckingPage(webDriver);
    }
}
