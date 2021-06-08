package steps.profileSteps;

import baseEntities.BaseStep;
import pages.profile.ProfileEditPage;
import utils.Utils;

import java.util.Objects;

public class ProfileEditPageSteps extends BaseStep<ProfileEditPage> {

    public ProfileEditPageSteps() {
        super(false);
    }

    public ProfileEditPageSteps fillLastNameField(){
        int maxLength;
//        maxLength = Integer.parseInt(Objects.requireNonNull(this.page.getNickInput().getAttribute("maxlength")));
//        this.page.getNickInput().sendKeys(Utils.getRandomAlphaNumericString(maxLength + 1));
        maxLength = Integer.parseInt(Objects.requireNonNull(this.page.getLastNameInput().getAttribute("maxlength")));
        this.page.getLastNameInput().sendKeys(Utils.getRandomAlphaNumericString(maxLength + 1));
        return this;
    }

    public ProfileEditPageSteps savingWithIncorrectData(){
        this.page.getSaveBtn().click();
        return this;
    }

}
