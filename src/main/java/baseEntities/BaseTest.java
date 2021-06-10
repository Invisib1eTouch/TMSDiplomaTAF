package baseEntities;

import com.codeborne.selenide.testng.SoftAsserts;
import core.DriverClient;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(SoftAsserts.class)
public abstract class BaseTest {

    /**
     * Sets parameters for driver before all tests execution
     */
    @BeforeSuite
    public void driverSetup(){
        DriverClient.init();
    }
}
