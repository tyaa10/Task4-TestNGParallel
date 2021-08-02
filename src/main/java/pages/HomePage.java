package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WebDriverSingletone;

public class HomePage {

    @FindBy(name = "search")
    private WebElement searchField;

    private WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        // WebDriver webDriver = WebDriverSingletone.getDriver();
        System.out.println("HomePage T: " + Thread.currentThread().getName());
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
// searchThingsByKeyword
    private HomePage inputSearchKeyword (final String keyword) {
        searchField.sendKeys(keyword);
        WebDriverWait wait = new WebDriverWait(webDriver, 50);
        wait.until(
            driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        return new HomePage(webDriver);
    }

    private LaptopsPage selectSearchSuggestItem (final String keyword) {
        WebElement searchSuggestItemElement = webDriver.findElement(
            By.xpath(
                String.format("//div[contains(@class, 'search-suggest')]//p[contains(@class, 'search-suggest__heading') and text()[normalize-space(.)='Перейти в категорию']]/following-sibling::ul//a[contains(@class, 'search-suggest__item-text') and text()[normalize-space(.)='%s']]", keyword)
            )
        );
        searchSuggestItemElement.click();
        return new LaptopsPage(webDriver);
    }

    public LaptopsPage searchByKeyword (final String keyword) {
        return inputSearchKeyword(keyword).selectSearchSuggestItem(keyword);
    }
}
