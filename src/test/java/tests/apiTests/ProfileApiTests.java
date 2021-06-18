package tests.apiTests;

import apiSteps.UserApiSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileApiTests {

    @Test
    public void correctEmailInPersonalDataTest() {
        String login = "ktafaniuk@yandex.by";
        String password = "Qwerty_2206";
        UserApiSteps.login(login, password);
        var personalDataResponse = UserApiSteps.getMe();
        Assert.assertEquals(personalDataResponse.path("email"), login);
        Assert.assertEquals(personalDataResponse.getStatusCode(), 200);
    }

    @Test
    public void getPersonalDataForUnauthorizedUserTest() {
        var personalDataResponse = UserApiSteps.getMe();
        Assert.assertEquals(personalDataResponse.getStatusCode(), 401);
    }

    @Test
    public void getInternalUserInfoWithNonexistentIdTest() {
        final int nonexistentUserId = 35311180;
        var internalUserInfoResponse = UserApiSteps.getInternalUserInfoById(nonexistentUserId);
        Assert.assertEquals(internalUserInfoResponse.getStatusCode(), 404);
    }
}
