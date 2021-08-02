package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WebDriverSingletone;

import java.util.List;

public class LaptopsPage{

    @FindBy(name = "searchline")
    private WebElement brandSearchField;

    @FindBy(xpath = "//a[@class='checkbox-filter__link']//label[contains(text(),'HP')]")
    private WebElement brand1;

    @FindBy(xpath = "//a[@class='checkbox-filter__link']//label[contains(text(),'Acer')]")
    private WebElement brand2;

    @FindBy(xpath = "//a[@class='checkbox-filter__link']//label[contains(text(),'Apple')]")
    private WebElement brand3;

    @FindBy(xpath = "//div[@data-filter-name='producer']//label")
    private List<WebElement> brandList;

    @FindBy(xpath="//select//option")
    private List<WebElement> optionsElements;

    @FindBy(xpath ="//a[@class='goods-tile__picture ng-star-inserted']")
    private List<WebElement> mostExpensiveDevice;

    private WebDriver webDriver;

    public LaptopsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        // WebDriver webDriver = WebDriverSingletone.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public void searchByKeyword (final String keyword) { brandSearchField.sendKeys(keyword, Keys.ENTER); }

//    public void clickOnBrandList() {
//        int i = 0;
//        for (WebElement brandElement : brandList) {
//            if (brandElement[i]<3) { // Array type expected; found: 'org.openqa.selenium.WebElement'
//                brandElement.click();
//                break;
//            }
//        }
//    }

    public void clickOnBrand1() { brand1.click();}
    public void clickOnBrand2() { brand2.click();}
    public void clickOnBrand3() { brand3.click();}

    public void chooseElementOptions() {
        for (WebElement webElement : optionsElements) {
            if (webElement.getText().equals("От дорогих к дешевым")) {
                webElement.click();
                break;
            }
        }
    }

    public void chooseMostExpensiveDevice() {
        WebDriverWait wait = new WebDriverWait(webDriver, 50);
        wait.until(ExpectedConditions.visibilityOfAllElements(mostExpensiveDevice));
        mostExpensiveDevice.get(0).click();}

}

