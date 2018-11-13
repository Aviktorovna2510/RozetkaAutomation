package utils.reporting;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.util.Arrays;

/**
 * This class is used for EventFiringWebDriver creation
 */
public class EventListener extends AbstractWebDriverEventListener {

    private final static Logger logger = Logger.getLogger(EventListener.class);

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        logger.info("Loading url " + url);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        logger.info(url + " loaded");
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        if (element != null) {
            logger.info("Element was found: " + element.getText());
        }

        logger.info("Element was found: " + by);
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        logger.info("Trying to click on element : " + element.getText());
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        logger.info("Trying to send text: " + Arrays.toString(keysToSend) + " to input field");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        logger.error("\n" + "-------------------------------------------------\n" +
                throwable + "\n" +
                "--------------------------------------------------");
    }
}