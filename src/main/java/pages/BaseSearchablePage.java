package pages;

import decorator.custom.webelements.Button;
import decorator.custom.webelements.TextInput;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class BaseSearchablePage extends AbstractPage {

    @FindBy(name = "search")
    private TextInput searchField;

    public BaseSearchablePage(WebDriver driver) {
        super(driver);
    }

    public BaseSearchablePage inputSearchKeyword (final String keyword) throws InterruptedException {
        searchField.safeSendKeys(30, keyword);
        return new BaseSearchablePage(driver);
    }

    public ProductsPage selectSearchSuggestItem (final String keyword) {
        Button searchSuggestItemElement =
            new Button(
                driver,
                driver.findElement(
                    By.xpath(
                        String.format("//div[contains(@class, 'search-suggest')]//p[contains(@class, 'search-suggest__heading') and text()[normalize-space(.)='Перейти в категорию']]/following-sibling::ul//a[contains(@class, 'search-suggest__item-text') and text()[normalize-space(.)='%s']]", keyword)
                    )
                )
            ){};
        searchSuggestItemElement.safeClickThenWaitForUpdate(15);
        return new ProductsPage(driver);
    }
}
