package labTest;

import model.RozetkaFilter;
import model.RozetkaFilters;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AddToCartPage;
import pages.HomePage;
import pages.LaptopsPage;
import pages.SumCheckingPage;
import util.WebDriverSingletone;
import util.XMLtoObject;

import java.util.List;

public class SumVerifyTest {

        private WebDriverSingletone webDriverSingletone;

        @BeforeClass
        public void appSetup () {
            System.out.println("appSetup T: " + Thread.currentThread().getName());
            webDriverSingletone = WebDriverSingletone.getInstance();
        }

        @BeforeMethod
        public void testsSetUp() {
            System.out.println("testsSetUp T: " + Thread.currentThread().getName());
            WebDriver webDriver = webDriverSingletone.getDriver();
            webDriver.manage().window().maximize();
            webDriver.get("https://rozetka.com.ua/");
        }

        @DataProvider(parallel = true)
        public Object[][] products() {
            XMLtoObject xmLtoObject = new XMLtoObject();
            RozetkaFilters rozetkaFilters = xmLtoObject.convert();
            List<RozetkaFilter> rozetkaFilterList = rozetkaFilters.getRozetkaFilters();
            int rowAmount = rozetkaFilterList.size();
            int columnAmount = 1;
            Object[][] rozetkaFilterArray = new Object[rowAmount][columnAmount];
            for (int i = 0; i < rozetkaFilterList.size(); i++) {
                rozetkaFilterArray[i][0] = rozetkaFilterList.get(i);
            }
            return rozetkaFilterArray;
        }


        @Test(dataProvider = "products")
        public void verifySum (RozetkaFilter rozetkaFilter){
            System.out.println("verifySum T: " + Thread.currentThread().getName());
            System.out.println("Product Id: " + rozetkaFilter.getId());
            WebDriver webDriver = webDriverSingletone.getDriver();
            WebDriverWait wait = new WebDriverWait(webDriver, 50);
            new HomePage(webDriver).searchByKeyword(rozetkaFilter.getProductGroup());
            new LaptopsPage(webDriver).searchByKeyword(rozetkaFilter.getBrand());
            new LaptopsPage(webDriver).clickOnBrand1();
            new LaptopsPage(webDriver).chooseElementOptions();
            wait.until(
                    driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
            new LaptopsPage(webDriver).chooseMostExpensiveDevice();
            new AddToCartPage(webDriver).pressButtonBuy();
            wait.until(
                    driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
            Assert.assertTrue(new SumCheckingPage(webDriver).checkProductSum(rozetkaFilter.getSum()));
        }

        @AfterMethod
        public void tearDown() {
            System.out.println("tearDown T: " + Thread.currentThread().getName());
            webDriverSingletone.closeDriver();
        }
    }

