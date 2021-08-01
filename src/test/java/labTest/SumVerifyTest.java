package labTest;

import model.RozetkaFilter;
import model.RozetkaFilters;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AddToCartPage;
import pages.HomePage;
import pages.LaptopsPage;
import pages.SumCheckingPage;
import util.WebDriverSingletone;
import util.XMLtoObject;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SumVerifyTest {

        private WebDriver webDriver;

        @BeforeTest
        public void setUp() { // Properties вже с Cинглтоном?
            WebDriver webDriver = WebDriverSingletone.getDriver();
        }

        @BeforeMethod
        public void testsSetUp() {
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
//        webDriver.get("https://rozetka.com.ua/");
            webDriver.get(webDriver.getCurrentUrl());
            webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        @DataProvider(parallel = true)
        public Object[][] products() {
            XMLtoObject xmLtoObject = new XMLtoObject();
            RozetkaFilters rozetkaFilters = xmLtoObject.convert();
            List<RozetkaFilter> rozetkaFilterList = rozetkaFilters.getRozetkaFilters();
            int rowAmount = rozetkaFilterList.size(); //разобраться с [][], падает на этой строке rozetkaFilterList&quot; is null
            int columnAmount = 1;
            Object[][] rozetkaFilterArray = new Object[rowAmount][columnAmount];
            for (int i = 0; i < rozetkaFilterList.size(); i++) {
                rozetkaFilterArray[i][0] = rozetkaFilterList.get(i);
            }

            return rozetkaFilterArray;
        }


        @Test(dataProvider = "products")

        public void verifySum (RozetkaFilter rozetkaFilter){
//        XMLtoObject xmLtoObject = new XMLtoObject();
//        RozetkaFilters rozetkaFilters = xmLtoObject.convert();
            WebDriverWait wait = new WebDriverWait(webDriver, 50);

            new HomePage().searchByKeyword(rozetkaFilter.getProductGroup());
            new LaptopsPage().searchByKeyword(rozetkaFilter.getBrand());
            new LaptopsPage().clickOnBrand1();
            new LaptopsPage().chooseElementOptions();
            wait.until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new LaptopsPage().chooseMostExpensiveDevice();
            new AddToCartPage().pressButtonBuy();
            wait.until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            Assert.assertTrue(new SumCheckingPage().checkProductSum(rozetkaFilter.getSum()));
        }

//    @AfterMethod
//    public void tearDown() { WebDriverSingletone.closeDriver(); }

    }

//        int rowAmount = rozetkaFilterList.size();
//        int columnAmount = 2;
//        Object[][] rozetkaTable = new Object[rowAmount][columnAmount];
//        for (int i = 0; i < rozetkaFilterList.size(); i++) {
//            Object[] columnOfTable = rozetkaTable[i];
//            columnOfTable[0] = i + 1;
//            columnOfTable[1] = rozetkaFilterList.get(i);
//        }
//        return rozetkaTable;
//    }

