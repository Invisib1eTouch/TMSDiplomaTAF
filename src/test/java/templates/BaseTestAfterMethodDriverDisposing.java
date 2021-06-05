package templates;

import baseEntities.BaseTest;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterClass;

public class BaseTestAfterMethodDriverDisposing extends BaseTest {

    @AfterClass
    public void methodTeardown(){
        WebDriverRunner.closeWebDriver();
    }
}
