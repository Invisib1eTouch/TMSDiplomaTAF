package baseEntities;

import com.codeborne.selenide.SelenideElement;
import core.DriverClient;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.AllureUtilities;

import java.util.Objects;

public abstract class BaseTest {

    /**
     * Sets parameters for driver before all tests execution
     */
    @BeforeSuite
    public void driverSetup(){
        DriverClient.getInstance().setup();
    }

    @AfterSuite
    public void suiteTearDown(){
        AllureUtilities.removeParametersInReport();
    }

    protected int getElementTextLength(SelenideElement selenideElement) {
        return Objects.requireNonNull(selenideElement.getValue()).length();
    }
}
