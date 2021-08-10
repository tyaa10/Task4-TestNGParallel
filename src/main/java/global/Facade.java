package global;

import pages.*;
import util.WebDriverSingletone;

public class Facade {

    private final WebDriverSingletone webDriverSingletone;

    public Facade() {
        webDriverSingletone = WebDriverSingletone.getInstance();
    }

    public void open(String urlString) {
        webDriverSingletone.getDriver().get(urlString);
    }

    public void close() {
        webDriverSingletone.closeDriver();
    }

    public Facade filterProductsByCategory (final String category) throws InterruptedException {
        new BaseSearchablePage(webDriverSingletone.getDriver())
            .inputSearchKeyword(category)
            .selectSearchSuggestItem(category);
        return this;
    }

    public Facade filterProductsByBrand (final String brand) {
        new ProductsPage(webDriverSingletone.getDriver())
            .filterBrandsByKeyword(brand).selectBrand(brand);
        return this;
    }

    public Facade sortProductsFromExpensive() {
        new ProductsPage(webDriverSingletone.getDriver())
            .sortProductsFromExpensive();
        return this;
    }

    public Facade chooseFirstProduct() {
        new ProductsPage(webDriverSingletone.getDriver())
            .chooseFirstProduct();
        return this;
    }

    public Facade addProductToCart() {
        new ProductPage(webDriverSingletone.getDriver())
            .pressButtonBuy();
        return this;
    }

    public int getCartTotalPrice() {
        return new CartPage(webDriverSingletone.getDriver())
            .getOrderPriceTotal();
    }
}
