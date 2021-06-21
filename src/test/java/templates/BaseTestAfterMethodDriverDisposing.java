package templates;

import baseEntities.BaseTest;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterMethod;

public class BaseTestAfterMethodDriverDisposing extends BaseTest {

    @AfterMethod(alwaysRun = true)
    public void methodTeardown(){
        WebDriverRunner.closeWebDriver();
    }
}