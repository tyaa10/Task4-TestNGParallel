package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DomHelpers {
    public static WebElement waitForElementToBeRefreshedAndClickable(WebDriver driver, By by) {
        return new WebDriverWait(driver, 30)
            .until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(by)));
    }
    public static WebElement waitForElementToBeRefreshedAndClickable(WebDriver driver, WebElement parentWebElement, By by) {
        return new WebDriverWait(driver, 30)
            .until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(parentWebElement.findElement(by))));
    }
}
