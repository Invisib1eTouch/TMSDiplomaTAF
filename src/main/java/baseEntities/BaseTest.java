package baseEntities;

import core.DriverClient;
import org.testng.annotations.BeforeSuite;
import utils.CredsFactory;

public abstract class BaseTest {

    protected static volatile CredsFactory credsFactory;
    /**
     * Sets parameters for driver before all tests execution
     */
    @BeforeSuite
    public void driverSetup(){
        DriverClient.init();
        credsFactory = new CredsFactory();
    }
}
