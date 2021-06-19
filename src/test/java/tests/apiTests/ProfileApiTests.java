package tests.apiTests;

import apiSteps.UserApiSteps;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ProfileApiTests {

    @Test
    public void getInternalUserInfoWithNonexistentIdTest() {
        final int nonexistentUserId = 35311180;
        var internalUserInfoResponse = UserApiSteps.getInternalUserInfoById(nonexistentUserId);
        Assert.assertEquals(internalUserInfoResponse.getStatusCode(), 404);
    }

    @Test(dependsOnMethods = "getInternalUserInfoWithNonexistentIdTest")
    public void getPersonalDataForUnauthorizedUserTest() {
        var personalDataResponse = UserApiSteps.getMe();
        Assert.assertEquals(personalDataResponse.getStatusCode(), 401);
    }

    @Test(dependsOnMethods = "getPersonalDataForUnauthorizedUserTest")
    @Parameters({"validLogin_3", "validPassword_3"})
    public void correctEmailInPersonalDataTest(String email, String password) {
        UserApiSteps.login(email, password);
        var personalDataResponse = UserApiSteps.getMe();
        Assert.assertEquals(personalDataResponse.path("email"), email);
        Assert.assertEquals(personalDataResponse.getStatusCode(), 200);
    }
}
