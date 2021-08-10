package pages;

import decorator.CustomWebElementFieldDecorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    protected WebDriver driver;
    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(
            webDriver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .equals("complete")
        );
        PageFactory.initElements(new CustomWebElementFieldDecorator(driver), this);
    }
}
