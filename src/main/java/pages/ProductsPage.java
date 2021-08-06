package pages;

import decorator.custom.webelements.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductsPage extends AbstractPage {

    @FindBy(xpath = "//div[@data-filter-name='producer']")
    private Block brandSearchBlock;
    @FindBy(xpath = "(//a[@class='goods-tile__picture ng-star-inserted'])[1]")
    private ImageButton firstProductImageButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public ProductsPage filterBrandsByKeyword (final String keyword) {
        new TextInput(
            driver,
            brandSearchBlock.findElement(By.xpath("//input[@name='searchline']"))
        ).safeSendKeysAndWaitForUpdate(brandSearchBlock, 15, keyword);
        return new ProductsPage(driver);
    }

    public ProductsPage selectBrand (final String brandName) {
        new CheckBoxFilterItem(
            driver,
            brandSearchBlock.findElement(
                By.xpath(
                    String.format("//label[contains(text(),'%s')]/parent::a", brandName)
                )
            )
        ).setCheckedThenWaitForUpdate(true, brandSearchBlock, 15);
        return new ProductsPage(driver);
    }

    private ProductsPage sortProductsByValue(String orderValue) {
        Select sortSelect =
            new Select(driver.findElement(By.xpath("//rz-sort/select")));
        AbstractElement.performAndWaitForUpdate(
            driver,
            () -> sortSelect.selectByValue(orderValue),
            /* new Runnable() {
                @Override
                public void run() {
                    sortSelect.selectByValue(orderValue);
                }
            }, */
            // ProductsPage::fooAction,
            firstProductImageButton,
            15
        );
        return new ProductsPage(driver);
    }

    private static void fooAction() {
        // ...
    }

    public ProductsPage sortProductsFromExpensive () {
        return sortProductsByValue("2: expensive");
    }

    public ProductPage chooseFirstProduct() {
        firstProductImageButton.moveToAndSafeClick(15);
        return new ProductPage(driver);
    }
}

