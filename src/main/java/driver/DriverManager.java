package driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import utils.reporting.EventListener;

import static constants.Constants.MAC_OS_PATH;
import static constants.Constants.OPERATION_SYSTEM;
import static constants.Constants.WINDOWS_PATH;

/**
 * This class provides methods for working with the driver
 */
public class DriverManager {

    private static WebDriver driver;

    private final static Logger logger = Logger.getLogger(DriverManager.class);

    public static WebDriver setDriver() {

        if (OPERATION_SYSTEM.contains("Mac OS")) {
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, MAC_OS_PATH);
        } else if (OPERATION_SYSTEM.contains("Windows")) {
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, WINDOWS_PATH);
        }
        driver = new ChromeDriver(OptionsHelper.getChromeOptions());

        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        WebDriverEventListener listener = new EventListener();
        eventDriver.register(listener);
        logger.info("New ChromeDriver instance for " + OPERATION_SYSTEM + " has been initialized");

        return eventDriver;
    }

    public static WebDriver getCurrentDriver() {
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
