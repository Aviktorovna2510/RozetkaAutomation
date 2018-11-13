package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.waiters.WebDriverWaitHelper;

import java.util.List;

/**
 * This class contains basic actions for working with elements and
 * can be useful for all pages objects
 */
public class BaseLogic {

    public WebDriver driver;
    private WebDriverWait wait;

    BaseLogic(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = WebDriverWaitHelper.generateWaits(driver, 40, 40, 1);
    }

    /**
     * Waits until web-element is visible on a page
     *
     * @param element the web-element, which should be visible
     * @return the passed element will be returned
     */
    WebElement waitForVisible(WebElement element) {
        waitForJSToBeLoaded();
        wait.until(ExpectedConditions.visibilityOf(element));
        highlightElement(element);

        return element;
    }

    /**
     * Waits until all web-elements in list are visible
     *
     * @param elements the list of web-elements
     * @return the same list of elements will be returned
     */
    List<WebElement> waitForVisible(List<WebElement> elements) {
        waitForJSToBeLoaded();
        for (WebElement element : elements) {
            wait.until(ExpectedConditions.visibilityOf(element));
        }

        return elements;
    }

    /**
     * Clicks a web-element after it becomes visible
     *
     * @param element a web-element
     * @return the passed element will be returned
     */
    WebElement clickWhenReady(WebElement element) {
        waitForVisible(element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();

        return element;
    }

    /**
     * Sends keys to a web-element after it becomes clear and visible
     *
     * @param element a web-element
     * @param text    a text to send
     * @return the passed element will be returned
     */
    WebElement sendText(WebElement element, String text) {
        waitForVisible(element);
        element.clear();
        element.sendKeys(text);
        for (String inputText = element.getAttribute("value"); inputText.length() != text.length(); ) {
            element.clear();
            element.sendKeys(text);
            inputText = element.getAttribute("value");
        }

        return element;
    }

    /**
     * Performs click on WebElement with JavaScriptExecutor
     *
     * @param element - a web-element
     */

    public void clickOnElementWithJSExecutor (WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    /**
     * Performs highlighting of incoming WebElement
     *
     * @param element - a web-element
     */
    private void highlightElement(WebElement element) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("element = arguments[0];" +
                    "original_style = element.getAttribute('style');" +
                    "element.setAttribute('style', original_style + \"; background: lightblue; border: 3px dashed black;\");" +
                    "setTimeout(function(){" +
                    "element.setAttribute('style', original_style);" +
                    "}, 60);", element);
        }
    }

    /**
     * Waits until all JavaScript code is executed in a page
     */
    public void waitForJSToBeLoaded() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
