package templates;

import baseEntities.BaseTest;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterClass;

public class BaseTestAfterClassDriverDisposing extends BaseTest {

    @AfterClass(alwaysRun = true)
    public void classTeardown(){
        WebDriverRunner.closeWebDriver();
    }
}