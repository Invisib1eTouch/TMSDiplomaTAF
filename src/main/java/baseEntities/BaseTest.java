package baseEntities;

import core.DriverClient;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.AllureUtilities;

public abstract class BaseTest {

    /**
     * Sets parameters for driver before all tests execution
     */
    @BeforeSuite
    public void driverSetup(){
        DriverClient.get().init();
    }

    @AfterSuite
    public void suiteTearDown(){
        AllureUtilities.removeParametersInReport();
    }
}
