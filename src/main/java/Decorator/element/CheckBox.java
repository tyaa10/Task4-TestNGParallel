//package Decorator.element;
//
//import org.openqa.selenium.WebElement;
//
//import javax.swing.*;
//
//public class CheckBox extends AbstractElement {
//
//    public CheckBox(WebElement Decorator.element) {
//        super(Decorator.element);
//    }
//
//    public void setChecked(boolean shouldBeChecked) {
//        if ((Decorator.element.isSelected() && !shouldBeChecked) || (!Decorator.element.isSelected() && shouldBeChecked)) {
//            Decorator.element.click();
//        } else {
//            System.out.println("Element already is Checked");
//        }
//    }
//}
