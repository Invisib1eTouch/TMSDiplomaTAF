package core;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

/**
 * Setup selenide config
 */
public class DriverClient {

    public static void init() {
//        Configuration.browser = PropertyReader.getBrowserName();
//        Configuration.startMaximized = true;

        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.driverManagerEnabled = false;
        Configuration.browser = PropertyReader.getBrowserName();
        Configuration.startMaximized = true;
        Configuration.browserCapabilities = new DesiredCapabilities();
        Configuration.browserCapabilities.setCapability("browserName", PropertyReader.getBrowserName());
//        Configuration.browserCapabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVNC", true,
//                "enableVideo", true
//        ));


        Configuration.pageLoadTimeout = 60000L;
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-infobars");
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
    }
}
