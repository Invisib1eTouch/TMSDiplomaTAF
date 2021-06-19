package baseEntities;

import core.DriverClient;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {

    /**
     * Sets parameters for driver before all tests execution
     */
    @BeforeSuite
    public void driverSetup(){
        DriverClient.get().init();
    }
}
