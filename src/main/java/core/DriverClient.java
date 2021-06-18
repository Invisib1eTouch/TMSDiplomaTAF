package core;


import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Setup selenide config
 */
public class DriverClient {

    public static void init() {
        Configuration.browser = PropertyReader.getBrowserName();
        Configuration.startMaximized = true;

//        Configuration.remote = "http://localhost:4444/wd/hub";
//        Configuration.driverManagerEnabled = false;
//        Configuration.browser = PropertyReader.getBrowserName();
//        Configuration.startMaximized = true;
//        Configuration.browserCapabilities = new DesiredCapabilities();
//        Configuration.browserCapabilities.setCapability("browserName", PropertyReader.getBrowserName());
//        Configuration.browserCapabilities.setCapability("enableVNC", true);
//        Configuration.browserCapabilities.setCapability("enableVideo", true);
//        Configuration.browserCapabilities.setCapability("sessionTimeout", "5m");
//        Configuration.browserCapabilities.setCapability("--no-sandbox", true);
//        Configuration.browserCapabilities.setCapability("--disable-gpu", true);
    }
}
