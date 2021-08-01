//package Decorator.element;
//
//import org.openqa.selenium.*;
//
//import java.util.List;
//
//public class AbstractElement implements WebElement {
//
//    protected WebElement Decorator.element;
//
//    public AbstractElement(WebElement Decorator.element) {
//        this.Decorator.element = Decorator.element;
//    }
//
//    @Override
//    public void click() {
//        Decorator.element.click();
//    }
//
//    @Override
//    public void submit() {
//        Decorator.element.submit();
//    }
//
//    @Override
//    public void sendKeys(CharSequence... charSequences) {
//        Decorator.element.sendKeys();
//    }
//
//    @Override
//    public void clear() {
//        Decorator.element.clear();
//    }
//
//
//    @Override
//    public boolean isSelected() {
//        Decorator.element.isSelected();
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        Decorator.element.isEnabled();
//        return false;
//    }
//
//    @Override
//    public boolean isDisplayed() {
//        Decorator.element.isDisplayed();
//        return false;
//    }
//
//    @Override
//    public String getText() {
//        return null;
//    }
//
//    @Override
//    public List<WebElement> findElements(By by) {
//        return null;
//    }
//
//    @Override
//    public WebElement findElement(By by) {
//        return null;
//    }
//
//    @Override
//    public Point getLocation() {
//        return null;
//    }
//
//    @Override
//    public Dimension getSize() {
//        return null;
//    }
//
//    @Override
//    public Rectangle getRect() {
//        return null;
//    }
//
//    @Override
//    public String getCssValue(String s) {
//        return null;
//    }
//
//    @Override
//    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
//        return null;
//    }
//
//    @Override
//    public String getTagName() {
//        return null;
//    }
//
//    @Override
//    public String getAttribute(String s) {
//        return null;
//    }
//}
//
