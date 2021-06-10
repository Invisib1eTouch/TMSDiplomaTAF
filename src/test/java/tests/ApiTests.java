package tests;

import apiSteps.UserApiSteps;
import org.testng.annotations.Test;

public class ApiTests {

    @Test
    public void ApiTest(){
        UserApiSteps.login("test.oleksandr.oleksandrov@gmail.com", "UltraPassword");
        var test = UserApiSteps.getMe().getBody().asString();

        System.out.println(test);
    }
}
