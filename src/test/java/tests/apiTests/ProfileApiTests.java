package tests.apiTests;

import apiSteps.UserApiSteps;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.AllureUtilities;
import utils.Utils;

public class ProfileApiTests {
    @AfterClass
    public void tearDown() throws Exception {
        AllureUtilities.removeParametersInReport();
    }

    @Test
    public void getInternalUserInfoWithNonexistentIdTest() {
        final String nonexistentUserId = Utils.getRandomAlphaNumericString(5);
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
