//package Decorator.element;
//
//import org.openqa.selenium.ElementClickInterceptedException;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import util.WebDriverSingletone;
//
//public class Button extends AbstractElement {
//
//    public Button(WebElement Decorator.element) {
//        super(Decorator.element);
//    }
//
//    public void safeClick() {
//        try {
//            Decorator.element.click();
//        } catch (ElementClickInterceptedException e) {
//            new WebDriverWait(WebDriverSingletone.getDriver(), 50).until(ExpectedConditions.elementToBeClickable(Decorator.element));
//            Decorator.element.click();
//        }
//    }
//}
