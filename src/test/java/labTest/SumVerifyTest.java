package labTest;

import global.Facade;
import model.RozetkaFilter;
import model.RozetkaFilters;
import org.testng.Assert;
import org.testng.annotations.*;
import util.XMLtoObject;

import java.util.List;

public class SumVerifyTest {

        private Facade domManipulatorFacade;

        @BeforeClass
        public void appSetup () {
            domManipulatorFacade = new Facade();
        }

        @BeforeMethod
        public void testsSetUp() {
            domManipulatorFacade.open("https://rozetka.com.ua/");
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
        public void givenFilter_whenTheMostExpensiveProductAddedToCart_thenTotalPriceLessThanBound (RozetkaFilter rozetkaFilter) {
            int actualOrderPriceTotal =
                domManipulatorFacade.filterProductsByCategory(rozetkaFilter.getProductGroup())
                    .filterProductsByBrand(rozetkaFilter.getBrand())
                    .sortProductsFromExpensive()
                    .chooseFirstProduct()
                    .addProductToCart()
                    .getCartTotalPrice();
            int expectedOrderPriceTotalMaxBound = rozetkaFilter.getSum();
            Assert.assertTrue(actualOrderPriceTotal < expectedOrderPriceTotalMaxBound);
        }

        @AfterMethod
        public void tearDown() {
            domManipulatorFacade.close();
        }
    }

