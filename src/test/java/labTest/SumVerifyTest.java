package labTest;

import global.Facade;
import io.qameta.allure.*;
import model.RozetkaFilter;
import model.RozetkaFilters;
import model.ValueWrapper;
import org.testng.Assert;
import org.testng.annotations.*;
import testlisteners.AllureTestListener;
import util.XMLtoObject;

import java.util.List;

@Listeners({AllureTestListener.class})
@Epic("Rozetka UI Tests")
@Feature("Shopping Tests")
public class SumVerifyTest {

        private Facade domManipulatorFacade;

        @BeforeClass
        public void appSetup () {
            domManipulatorFacade = new Facade();
        }

        @BeforeMethod
        @Step("Go to https://rozetka.com.ua/")
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
        @Severity(SeverityLevel.BLOCKER)
        @Story("Search for the most expensive item in a given category, with a given brand and add it to the cart")
        @Description("After searching for the most expensive product in a given category, with a given brand and adding it to the shopping cart, the name of the product and its price must match the name and price indicated on the product card")
        public void givenFilter_whenTheMostExpensiveProductAddedToCart_thenTotalPriceLessThanBound (RozetkaFilter rozetkaFilter) throws InterruptedException {
            AllureLifecycle lifecycle = Allure.getLifecycle();
            lifecycle.updateTestCase(
                testResult ->
                    testResult.setName(
                        String.format(
                            "Test the most expensive product title and price for '%s' category and '%s' brand",
                            rozetkaFilter.getProductGroup(),
                            rozetkaFilter.getBrand()
                        )
                    )
            );
            lifecycle.updateTestCase(
                testResult ->
                    testResult.setDescription(
                        String.format(
                            "After searching for the most expensive product in '%s' category, with '%s' brand and adding it to the shopping cart, the name of the product and its price must match the name and price indicated on the product card",
                            rozetkaFilter.getProductGroup(),
                            rozetkaFilter.getBrand()
                        )
                    )
            );
            ValueWrapper<String> productTitleFromProduct = new ValueWrapper<>();
            ValueWrapper<String> productTitleFromCart = new ValueWrapper<>();
            ValueWrapper<Integer> cartTotalPrice = new ValueWrapper<>();
            domManipulatorFacade.filterProductsByCategory(rozetkaFilter.getProductGroup())
                .filterProductsByBrand(rozetkaFilter.getBrand())
                .sortProductsFromExpensive()
                .chooseFirstProduct()
                .getProductTitleFromProduct(productTitleFromProduct)
                .addProductToCart()
                .getProductTitleFromCart(productTitleFromCart)
                .getCartTotalPrice(cartTotalPrice);
            int expectedOrderPriceTotalMaxBound = rozetkaFilter.getSum();
            verifyProductTitle(productTitleFromCart.value, productTitleFromProduct.value);
            verifyProductPrice(cartTotalPrice.value, expectedOrderPriceTotalMaxBound);
        }

        @Test(dataProvider = "products")
        public void givenFilter_whenProductsPageSearch_thenSuccess (RozetkaFilter rozetkaFilter) throws InterruptedException {
            domManipulatorFacade.filterProductsByCategory(rozetkaFilter.getProductGroup())
                .filterProductsByCategory(rozetkaFilter.getProductGroup());
        }

        @AfterMethod
        public void tearDown() {
            domManipulatorFacade.close();
        }

        @Step("Verify product title: {productTitleFromShoppingCart} should be equal to {productTitleFromProductCard}")
        private void verifyProductTitle(String productTitleFromShoppingCart, String productTitleFromProductCard){
            Assert.assertEquals(productTitleFromShoppingCart, productTitleFromProductCard);
        }

        @Step("Verify product price: {productTotalPriceFromShoppingCart} should be less than {expectedOrderPriceTotalMaxBound}")
        private void verifyProductPrice(int productTotalPriceFromShoppingCart, int expectedOrderPriceTotalMaxBound){
            Assert.assertTrue(productTotalPriceFromShoppingCart < expectedOrderPriceTotalMaxBound);
        }
}

