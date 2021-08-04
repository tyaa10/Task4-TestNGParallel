package decorator;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.*;

public class CustomWebElementFieldDecorator extends DefaultFieldDecorator {
    private final WebDriver driver;
    public CustomWebElementFieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
        this.driver = (WebDriver) searchContext;
    }
    /**
     * Метод вызывается фабрикой для каждого поля в классе
     */
    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<?> decoratableClass = decoratableClass(field);
        // если класс поля декорируемый
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }
            // элемент
            return createElement(loader, locator, decoratableClass);
        }
        return super.decorate(loader, field);
    }
    /**
     * Возвращает декорируемый класс поля,
     * либо null если класс не подходит для декоратора
     */
    protected Class<?> decoratableClass(Field field) {
        Class<?> clazz = field.getType();
        // у элемента должен быть конструктор, принимающий WebElement
        try {
            clazz.getConstructor(WebDriver.class, WebElement.class);
        } catch (Exception e) {
            return null;
        }
        return clazz;
    }
    /**
     * Создание элемента.
     * Находит WebElement и передает его в кастомный класс
     */
    protected <T> T createElement(
        ClassLoader loader,
        ElementLocator locator,
        Class<T> clazz
    ) {
        WebElement proxy = proxyForLocator(loader, locator);
        return createInstance(clazz, proxy);
    }
    /**
     * Создает экземпляр класса,
     * вызывая конструктор с аргументом WebElement
     */
    private <T> T createInstance(Class<T> clazz, WebElement element) {
        try {
            return (T) clazz.getConstructor(WebDriver.class, WebElement.class)
                .newInstance(driver, element);
        } catch (Exception e) {
            throw new AssertionError(
                "WebElement can't be represented as " + clazz
            );
        }
    }
}
