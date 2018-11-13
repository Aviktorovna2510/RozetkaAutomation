package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BaseLogic {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(@class, 'header-search')]//input[@type='text']")
    private WebElement inputSearch;

    @Step("Type search value")
    public void typeValueIntoSearchField(String product) {
        clickWhenReady(inputSearch);
        sendText(inputSearch, product).sendKeys(Keys.ENTER);
    }
}
