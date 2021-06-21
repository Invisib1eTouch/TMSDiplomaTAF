package core;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Setup selenide config
 */
public class DriverClient {

    private static DriverClient instance;

    public static DriverClient getInstance() {
        if (instance == null) {
            instance = new DriverClient();
            Configuration.browserCapabilities = new DesiredCapabilities();
        }
        return instance;
    }

    public DriverClient setup() {
        Configuration.browser = PropertyReader.getBrowserName();
        Configuration.startMaximized = true;
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--window-size=1920,1080");
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return this;
    }

    public DriverClient setupRemote() {
        this.setup();

        // Selenoid
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.driverManagerEnabled = false;
        Configuration.browserCapabilities.setCapability("browserName", PropertyReader.getBrowserName());
        return this;
    }

    public DriverClient enableHeadlessMode() {
        Configuration.headless = true;
        return this;
    }

    public DriverClient enableVNC() {
        Configuration.browserCapabilities.setCapability("enableVNC", true);
        return this;
    }

    public DriverClient enableVideo() {
        Configuration.browserCapabilities.setCapability("enableVideo", true);
        return this;
    }
}