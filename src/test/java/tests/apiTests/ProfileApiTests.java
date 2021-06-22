package tests.apiTests;

import apiSteps.ApiSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class ProfileApiTests {

    @Test(description = "Get user info with nonexistent id test")
    @Description("Get information about user with nonexistent id.")
    public void getInternalUserInfoWithNonexistentIdTest() {
        final String nonexistentUserId = "null";
        var internalUserInfoResponse = ApiSteps.get().userApiSteps()
                .getInternalUserInfoById(nonexistentUserId);

        Assert.assertEquals(internalUserInfoResponse.getStatusCode(), 404);
    }

    @Test(description = "Get personal data for unauthorized user test", dependsOnMethods = "getInternalUserInfoWithNonexistentIdTest")
    @Description("Get personal data for unauthorized user.")
    public void getPersonalDataForUnauthorizedUserTest() {
        var personalDataResponse = ApiSteps.get().userApiSteps()
                .getMe();

        Assert.assertEquals(personalDataResponse.getStatusCode(), 401);
    }

    @Test(description = "Verify correct email in personal data test", dependsOnMethods = "getPersonalDataForUnauthorizedUserTest")
    @Description("Verify correct user's email in personal data.")
    @Parameters({"validLogin_1", "validPassword_1"})
    public void correctEmailInPersonalDataTest(String email, String password) {
        var personalDataResponse = ApiSteps.get()
                .setToken(email, password)
                .userApiSteps()
                .getMe();

        Assert.assertEquals(personalDataResponse.path("email"), email);
        Assert.assertEquals(personalDataResponse.getStatusCode(), 200);
    }
}