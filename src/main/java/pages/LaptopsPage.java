package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LaptopsPage{

    // @FindBy(xpath = "//div[@data-filter-name='producer']//input[@name='searchline']")
    @FindBy(xpath = "//div[@data-filter-name='producer']")
    private WebElement brandSearchBlock;

    private By firstGoodLocator =
        By.xpath("(//a[@class='goods-tile__picture ng-star-inserted'])[1]");

    /* @FindBy(xpath = "//a[@class='checkbox-filter__link']//label[contains(text(),'HP')]")
    private WebElement brand1;

    @FindBy(xpath = "//a[@class='checkbox-filter__link']//label[contains(text(),'Acer')]")
    private WebElement brand2;

    @FindBy(xpath = "//a[@class='checkbox-filter__link']//label[contains(text(),'Apple')]")
    private WebElement brand3; */

    /* @FindBy(xpath = "//div[@data-filter-name='producer']//label")
    private List<WebElement> brandList; */

    /* @FindBy(xpath="//select//option")
    private List<WebElement> optionsElements; */

    /* @FindBy(xpath ="//a[@class='goods-tile__picture ng-star-inserted']")
    private List<WebElement> mostExpensiveDevice; */

    private WebDriver webDriver;

    public LaptopsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public LaptopsPage filterByBrand(String brand) {
        return filterBrandsByKeyword(brand).selectBrand(brand);
    }

    private LaptopsPage filterBrandsByKeyword (final String keyword) {
        WebDriverWait wait = new WebDriverWait(webDriver, 50);
        WebElement brandSearchInputElement =
            brandSearchBlock.findElement(By.xpath("//input[@name='searchline']"));
        wait.until(ExpectedConditions.elementToBeClickable(brandSearchInputElement));
        brandSearchInputElement.sendKeys(keyword);
        wait = new WebDriverWait(webDriver, 5);
        try {
            wait.until(ExpectedConditions.stalenessOf(brandSearchBlock));
        } catch (TimeoutException ignored) {}
        return new LaptopsPage(webDriver);
    }

    private LaptopsPage selectBrand (final String brandName) {
        WebElement brandSearchLabelElement =
            brandSearchBlock.findElement(
                By.xpath(
                    String.format("//label[contains(text(),'%s')]", brandName)
                )
            );
        WebDriverWait wait = new WebDriverWait(webDriver, 50);
        wait.until(ExpectedConditions.visibilityOf(brandSearchLabelElement));
        brandSearchLabelElement.click();
        return new LaptopsPage(webDriver);
    }

    public LaptopsPage sortThings(String orderText) {
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        WebElement firstGood = webDriver.findElement(
            By.xpath(
                "(//a[@class='goods-tile__picture ng-star-inserted'])[1]"
            )
        );
        Select sortSelect =
            new Select(webDriver.findElement(By.xpath("//rz-sort/select")));
        sortSelect.selectByVisibleText(orderText);
        try {
            wait.until(ExpectedConditions.stalenessOf(firstGood));
        } catch (TimeoutException ignored) {}
        return new LaptopsPage(webDriver);
    }

    public AddToCartPage chooseMostExpensiveGood() {
        WebDriverWait wait = new WebDriverWait(webDriver, 50);
        WebElement mostExpensiveGood = webDriver.findElement(
            By.xpath(
                "(//a[@class='goods-tile__picture ng-star-inserted'])[1]"
            )
        );
        wait.until(ExpectedConditions.visibilityOf(mostExpensiveGood));
        mostExpensiveGood.click();
        return new AddToCartPage(webDriver);
    }
}

