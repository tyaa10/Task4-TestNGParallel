package decorator.custom.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Button extends AbstractElement {
    public Button(WebDriver driver, WebElement element) {
        super(driver, element);
    }
    public void safeClick(long timeOutInSeconds) {
        safeAction(() -> element.click(), timeOutInSeconds);
    }
    public void safeClickThenWaitForUpdate(long timeOutInSeconds) {
        performAndWaitForUpdate(
            driver,
            () -> this.safeClick(timeOutInSeconds),
            15
        );
    }
    public void moveToAndSafeClick(long timeOutInSeconds) {
        moveToElementAndSafeAction(element, () -> element.click(), timeOutInSeconds);
    }
}
