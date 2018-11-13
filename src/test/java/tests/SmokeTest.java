package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.CataloguePage;
import pages.MainPage;
import pages.ProductPage;
import utils.reporting.AllureListener;

import static constants.Constants.BASE_URL;

@Listeners(AllureListener.class)
public class SmokeTest {

    private WebDriver driver;
    private MainPage mainPage;
    private CataloguePage cataloguePage;
    private ProductPage productPage;

    @BeforeClass
    public void start() {
        driver = DriverManager.setDriver();
        mainPage = new MainPage(driver);
        cataloguePage = new CataloguePage(driver);
        productPage = new ProductPage(driver);
        driver.get(BASE_URL);
    }

    @AfterClass
    public void close() {
        DriverManager.closeDriver();
    }

    @Parameters({"product", "manufacturer"})
    @Test(priority = 1)
    public void searchFunctionalityVerification(String product, String manufacturer) {
        mainPage.typeValueIntoSearchField(product);
        cataloguePage.verifyManufacturerFilter(manufacturer);
        cataloguePage.verifyProductListContainsProduct(product);
        cataloguePage.chooseProduct(0);
    }

    @Test(priority = 2)
    public void productPageVerification() {
        productPage.verifyProductDescription();
        productPage.verifyProductImage();
        productPage.verifyProductReviews();
    }

    @Test(priority = 3)
    public void productPurchaseVerification() {
        productPage.addProductToCart();
        productPage.verifyCartPopup();
        productPage.closeCartPopup();
        productPage.goToCart();
        productPage.verifyCartContent();
    }
}
