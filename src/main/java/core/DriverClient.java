package core;


import com.codeborne.selenide.Configuration;

public class DriverClient {

    public static void init() {
        Configuration.baseUrl = PropertyReader.getBaseUrl();
        Configuration.browser = PropertyReader.getBrowserName();
        Configuration.startMaximized = true;
    }
}
