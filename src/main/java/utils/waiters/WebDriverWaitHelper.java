package utils.waiters;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * This class create implicitly wait
 * and explicitly wait which  will ignore defined exceptions
 */
public class WebDriverWaitHelper {

    /**
     * @param driver      - driver
     * @param iWait       - implicit wait
     * @param eWait       - explicit wait
     * @param pollingTime - the interval taken between checking expected conditions (By default - FIVE_HUNDRED_MILLIS)
     */
    public static WebDriverWait generateWaits(WebDriver driver, int iWait, int eWait, int pollingTime) {
        driver.manage().timeouts().implicitlyWait(iWait, TimeUnit.SECONDS);
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, eWait);
        wait.pollingEvery(Duration.ofSeconds(pollingTime));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(ElementClickInterceptedException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(WebDriverException.class);
        wait.ignoring(Exception.class);

        return wait;
    }
}
