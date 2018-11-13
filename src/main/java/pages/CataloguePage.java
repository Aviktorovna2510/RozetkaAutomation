package pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;


public class CataloguePage extends BaseLogic {

    public CataloguePage(WebDriver driver) {
        super(driver);
    }

    private final static Logger logger = Logger.getLogger(CataloguePage.class);

    @FindBy(xpath = "//form[@id='filter_parameters_form']//a[@name='show_more_parameters'][1]")
    private WebElement filterShowMore;

    @FindBy(xpath = "//ul[@id='sort_producer']/li")
    private List<WebElement> allManufacturerFiltersElement;

    @FindBy(xpath = "//div[@id='catalog_goods_block']")
    private WebElement productBlock;

    private List<WebElement> allProductsOnPage = new ArrayList<>();

    @Step("Verify manufacturers name present in filter")
    public void verifyManufacturerFilter(String manufacturerName) {
        List<String> valuesInManufacturerFilter = new ArrayList<>();
        clickWhenReady(filterShowMore);
        waitForVisible(allManufacturerFiltersElement);
        allManufacturerFiltersElement.forEach(element -> valuesInManufacturerFilter.add(element.getText()));
        logger.info(valuesInManufacturerFilter.size() + " manufacturers in filter were found: ");
        valuesInManufacturerFilter.forEach(manufacturer -> logger.info(manufacturer));

        Assert.assertTrue(valuesInManufacturerFilter.contains(manufacturerName));
    }

    @Step("Verify search result page contains product")
    public void verifyProductListContainsProduct(String product) {
        waitForVisible(productBlock);
        allProductsOnPage = findAllProductsWithName(product);
        logger.info(allProductsOnPage.size() + " products contain search criteria " + product + " were found: ");
        allProductsOnPage.forEach(item -> logger.info(item.getText()));

        Assert.assertTrue(allProductsOnPage.size() > 2);
    }

    @Step("Choose product on search result page ")
    public void chooseProduct(int productIndex) {
        clickWhenReady(allProductsOnPage.get(productIndex));
    }

    private List<WebElement> findAllProductsWithName(String product) {
        return driver.findElements(By.xpath("//div[@id='catalog_goods_block']//a[contains(text(),'" + product + "')]"));
    }
}
