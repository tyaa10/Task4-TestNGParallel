package pages;

import decorator.custom.webelements.AbstractElement;
import decorator.custom.webelements.TextInput;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WebDriverSingletone;

public class HomePage extends AbstractPage {

    @FindBy(name = "search")
    private TextInput searchField;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage inputSearchKeyword (final String keyword) {
        searchField.safeSendKeys(30, keyword);
        return new HomePage(driver);
    }

    public LaptopsPage selectSearchSuggestItem (final String keyword) {
        AbstractElement searchSuggestItemElement =
            new AbstractElement(
                driver,
                driver.findElement(
                    By.xpath(
                        String.format("//div[contains(@class, 'search-suggest')]//p[contains(@class, 'search-suggest__heading') and text()[normalize-space(.)='Перейти в категорию']]/following-sibling::ul//a[contains(@class, 'search-suggest__item-text') and text()[normalize-space(.)='%s']]", keyword)
                    )
                )
            ){};
        AbstractElement.performAndWaitForUpdate(
            driver,
            searchSuggestItemElement::click,
            15
        );
        return new LaptopsPage(driver);
    }

    /* public LaptopsPage searchByKeyword (final String keyword) {
        return inputSearchKeyword(keyword).selectSearchSuggestItem(keyword);
    } */
}
