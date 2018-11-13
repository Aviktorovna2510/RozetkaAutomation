package pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ProductPage extends BaseLogic {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = Logger.getLogger(ProductPage.class);
    private static final String EXPECTED_CART_POP_UP_TITLE = "Вы добавили товар в корзину";
    private String productPageName;

    @FindBy(xpath = "//h1[@class='detail-title h1']")
    private WebElement productPageHeader;

    @FindBy(css = ".detail-col-description-indent")
    private WebElement productDescription;

    @FindBy(id = "base_image")
    private WebElement productMainImage;

    @FindBy(xpath = "//article[@class='pp-review-i']")
    private List<WebElement> productComments;

    @FindBy(xpath = "//button[@name='topurchases']")
    private WebElement productAddToCartButton;

    @FindBy(css = ".cart-title")
    private WebElement itemAddedToCartPopupTitle;

    @FindBy(css = ".popup-close-icon")
    private WebElement itemAddedToCartPopupCloseButton;

    @FindBy(xpath = "//*[@id='cart_popup_header']/div[@name='splash-button']")
    private WebElement cartIcon;

    @FindBy(id = "cart-popup")
    private WebElement cartPopup;

    @FindBy(xpath = "//div[@id='cart-popup']//a[@class='novisited cart-i-title-link']")
    private WebElement productInCartName;

    @Step("Verify product page contains description")
    public void verifyProductDescription() {
        waitForVisible(productPageHeader);
        productPageName = productPageHeader.getText();
        logger.info(productPageName + " product page was opened");
        waitForVisible(productDescription);
    }

    @Step("Verify product page contains main image")
    public void verifyProductImage() {
        waitForVisible(productMainImage);
    }

    @Step("Verify product page contains reviews")
    public void verifyProductReviews() {
        waitForVisible(productComments);
    }

    @Step("Add product to cart")
    public void addProductToCart() {
        clickWhenReady(productAddToCartButton);
    }

    @Step("Verify Item added to cart popup")
    public void verifyCartPopup() {
        waitForVisible(itemAddedToCartPopupTitle);

        Assert.assertEquals(itemAddedToCartPopupTitle.getText(), EXPECTED_CART_POP_UP_TITLE);
    }

    @Step("Close Item added to cart popup")
    public void closeCartPopup() {
        clickWhenReady(itemAddedToCartPopupCloseButton);
    }

    @Step("Go to cart")
    public void goToCart() {
        clickOnElementWithJSExecutor(cartIcon);
    }

    @Step("Verify cart contains product")
    public void verifyCartContent() {
        waitForVisible(cartPopup);
        waitForVisible(productInCartName);

        Assert.assertEquals(productInCartName.getText(), productPageName);
    }
}
