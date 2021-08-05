package global;

import pages.HomePage;
import pages.LaptopsPage;

public class Facade {

    private HomePage homePage;

    public Facade(HomePage homePage) {
        this.homePage = homePage;
    }

    public LaptopsPage getProductsByCategory (final String category) {
        return homePage.inputSearchKeyword(category).selectSearchSuggestItem(category);
    }
}
