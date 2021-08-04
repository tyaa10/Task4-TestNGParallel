package pages;

import decorator.custom.webelements.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DomHelpers;

public class LaptopsPage extends AbstractPage {

    @FindBy(xpath = "//div[@data-filter-name='producer']")
    private Block brandSearchBlock;
    @FindBy(xpath = "(//a[@class='goods-tile__picture ng-star-inserted'])[1]")
    private ImageButton firstProductImageButton;

    public LaptopsPage(WebDriver driver) {
        super(driver);
    }

    public LaptopsPage filterByBrand(String brand) {
        return filterBrandsByKeyword(brand).selectBrand(brand);
    }

    private LaptopsPage filterBrandsByKeyword (final String keyword) {
        new TextInput(
            driver,
            brandSearchBlock.findElement(By.xpath("//input[@name='searchline']"))
        ).safeSendKeysAndWaitForUpdate(brandSearchBlock, 15, keyword);
        return new LaptopsPage(driver);
    }

    private LaptopsPage selectBrand (final String brandName) {
        new CheckBoxFilterItem(
            driver,
            brandSearchBlock.findElement(
                By.xpath(
                    String.format("//label[contains(text(),'%s')]/parent::a", brandName)
                )
            )
        ).setCheckedThenWaitForUpdate(true, brandSearchBlock, 15);
        return new LaptopsPage(driver);
    }

    public LaptopsPage sortThings(String orderText) {
        Select sortSelect =
            new Select(driver.findElement(By.xpath("//rz-sort/select")));
        AbstractElement.performAndWaitForUpdate(
            driver,
            () -> sortSelect.selectByVisibleText(orderText),
            firstProductImageButton,
            15
        );
        return new LaptopsPage(driver);
    }

    public AddToCartPage chooseMostExpensiveGood() {
        firstProductImageButton.moveToAndSafeClick(15);
        return new AddToCartPage(driver);
    }
}

