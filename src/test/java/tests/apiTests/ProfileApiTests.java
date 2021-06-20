package tests.apiTests;

import apiSteps.ApiSteps;
import baseEntities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.Utils;

public class ProfileApiTests {

    @Test
    public void getInternalUserInfoWithNonexistentIdTest() {
        final String nonexistentUserId = Utils.getRandomAlphaNumericString(15);
        var internalUserInfoResponse = ApiSteps.get().userApiSteps()
                .getInternalUserInfoById(nonexistentUserId);

        Assert.assertEquals(internalUserInfoResponse.getStatusCode(), 404);
    }

    @Test(dependsOnMethods = "getInternalUserInfoWithNonexistentIdTest")
    public void getPersonalDataForUnauthorizedUserTest() {
        var personalDataResponse = ApiSteps.get().userApiSteps()
                .getMe();

        Assert.assertEquals(personalDataResponse.getStatusCode(), 401);
    }

    @Test(dependsOnMethods = "getPersonalDataForUnauthorizedUserTest")
    @Parameters({"validLogin_3", "validPassword_3"})
    public void correctEmailInPersonalDataTest(String email, String password) {
        var personalDataResponse = ApiSteps.get()
                .login(email, password)
                .userApiSteps()
                .getMe();

        Assert.assertEquals(personalDataResponse.path("email"), email);
        Assert.assertEquals(personalDataResponse.getStatusCode(), 200);
    }
}
