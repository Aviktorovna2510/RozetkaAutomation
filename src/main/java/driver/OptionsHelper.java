package driver;

import org.openqa.selenium.chrome.ChromeOptions;

/**
 * This class for passing capabilities to ChromeDriver.
 * With arguments  you can  customize and configure a ChromeDriver session.
 * See all options and descriptions https://peter.sh/experiments/chromium-command-line-switches/
 */
public class OptionsHelper {

    static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");

        return options;
    }
}
