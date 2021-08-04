package decorator.custom.webelements;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.Callable;

public class AbstractElement implements WebElement {

    protected WebDriver driver;
    protected WebElement element;

    public AbstractElement(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
    }

    public static void performAndWaitForUpdate(
        WebDriver driver,
        Runnable actionToPerform,
        long timeOutInSeconds
    ) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        actionToPerform.run();
        wait.until(
            webDriver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .equals("complete")
        );
    }

    public static <T> T performAndWaitForUpdate(
        WebDriver driver,
        Callable<T> actionToPerform,
        long timeOutInSeconds
    ) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        T result = actionToPerform.call();
        wait.until(
            webDriver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .equals("complete")
        );
        return result;
    }

    public static void performAndWaitForUpdate(
        WebDriver driver,
        Runnable actionToPerform,
        WebElement elementToWaitForUpdate,
        long timeOutInSeconds
    ) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        actionToPerform.run();
        try {
            wait.until(ExpectedConditions.stalenessOf(elementToWaitForUpdate));
        } catch (TimeoutException ignored) {}
    }

    public static <T> T performAndWaitForUpdate(
        WebDriver driver,
        Callable<T> actionToPerform,
        WebElement elementToWaitForUpdate,
        long timeOutInSeconds
    ) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        T result = actionToPerform.call();
        try {
            wait.until(ExpectedConditions.stalenessOf(elementToWaitForUpdate));
        } catch (TimeoutException ignored) {}
        return result;
    }

    public static void performAndWaitForUpdate(
        WebDriver driver,
        Runnable actionToPerform,
        By locatorToWaitForElementUpdate,
        long timeOutInSeconds
    ) {
        WebElement elementToWaitForUpdate = driver.findElement(locatorToWaitForElementUpdate);
        performAndWaitForUpdate(driver, actionToPerform, elementToWaitForUpdate, timeOutInSeconds);
    }

    public static <T> T performAndWaitForUpdate(
        WebDriver driver,
        Callable<T> actionToPerform,
        By locatorToWaitForElementUpdate,
        long timeOutInSeconds
    ) throws Exception {
        WebElement elementToWaitForUpdate = driver.findElement(locatorToWaitForElementUpdate);
        return performAndWaitForUpdate(driver, actionToPerform, elementToWaitForUpdate, timeOutInSeconds);
    }

    public void safeAction(Runnable actionToPerform, long timeOutInSeconds) {
        try {
            actionToPerform.run();
        } catch (ElementClickInterceptedException | StaleElementReferenceException ignored) {
            new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.refreshed(
                    ExpectedConditions.elementToBeClickable(element)));
            actionToPerform.run();
        }
    }

    public void moveToElementAndSafeAction(
        WebElement target,
        Runnable actionToPerform,
        long timeOutInSeconds
    ) {
        Actions actions = new Actions(driver);
        actions.moveToElement(target).perform();
        safeAction(actionToPerform, timeOutInSeconds);
    }

    public void moveToElementAndSafeAction(
        By targetLocator,
        Runnable actionToPerform,
        long timeOutInSeconds
    ) {
        Actions actions = new Actions(driver);
        WebElement target = driver.findElement(targetLocator);
        actions.moveToElement(target).perform();
        safeAction(actionToPerform, timeOutInSeconds);
    }

    @Override
    public void click() {
        element.click();
    }

    @Override
    public void submit() {
        element.submit();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        element.sendKeys();
    }

    @Override
    public void clear() {
        element.clear();
    }


    @Override
    public boolean isSelected() {
        return element.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public String getText() {
        return element.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return element.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return element.findElement(by);
    }

    @Override
    public Point getLocation() {
        return element.getLocation();
    }

    @Override
    public Dimension getSize() {
        return element.getSize();
    }

    @Override
    public Rectangle getRect() {
        return element.getRect();
    }

    @Override
    public String getCssValue(String s) {
        return element.getCssValue(s);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return getScreenshotAs(outputType);
    }

    @Override
    public String getTagName() {
        return element.getTagName();
    }

    @Override
    public String getAttribute(String s) {
        return element.getAttribute(s);
    }
}
