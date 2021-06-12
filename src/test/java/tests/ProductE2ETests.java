package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steps.MainPageSteps;
import templates.BaseTestAfterClassDriverDisposing;

public class ProductE2ETests extends BaseTestAfterClassDriverDisposing {

    private MainPageSteps mainPageSteps;

    @BeforeClass
    @Parameters({"validLogin_3", "validPassword_3"})
    public void loginBeforeTest(String login, String password) {
        this.mainPageSteps = new MainPageSteps(true)
                .loginWithCorrectCredentials(MainPageSteps.class, login, password);
    }

    @Test
    public void findProductTest() {
        // сделали запрос в апи, получили имя
       this.mainPageSteps.searchProduct("Samsung Galaxy A52 SM-A525F/DS 4GB/128GB (синий)");
        System.out.println();
    }
}
