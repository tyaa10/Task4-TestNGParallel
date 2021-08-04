package decorator.custom.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextInput extends AbstractElement {
    public TextInput(WebDriver driver, WebElement element) {
        super(driver, element);
    }
    public void safeSendKeys(long timeOutInSeconds, CharSequence... charSequences) {
        safeAction(() -> {
            element.clear();
            element.sendKeys(charSequences);
        }, timeOutInSeconds);
    }
    public void safeSendKeysAndWaitForUpdate(
        WebElement elementToWaitForUpdate,
        long timeOutInSeconds,
        CharSequence... charSequences
    ) {
        performAndWaitForUpdate(
            driver,
            () -> {
                element.clear();
                element.sendKeys(charSequences);
            },
            elementToWaitForUpdate,
            timeOutInSeconds
        );
    }
    @Override
    public void sendKeys(CharSequence... charSequences) {
        element.clear();
        element.sendKeys(charSequences);
    }
}

