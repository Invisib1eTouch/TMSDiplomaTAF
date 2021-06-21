package steps.profileSteps;

import io.qameta.allure.Step;
import pages.profile.PopupModal;
import pages.profile.ProfileHeader;
import steps.commonSteps.CommonHeaderSteps;
import steps.profileSteps.personalDataTab.ProfilePersonalDataTabSteps;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;

public abstract class ProfileHeaderSteps<Page extends ProfileHeader> extends CommonHeaderSteps<Page> {
    /**
     * @param openPageByUrl - if true page will be opened by url
     */
    public ProfileHeaderSteps(Boolean openPageByUrl) {
        super(openPageByUrl);
    }

    @Step("Open Personal data tab.")
    public ProfilePersonalDataTabSteps openPersonalDataTab() {
        this.page.getPersonalDataBtn().click();
        return new ProfilePersonalDataTabSteps(false);
    }

    /**
     * @param methodCallerStepsClass - steps class, that invokes method; necessary to know to maintain invocation chain
     * @param backgroundImageFile    - Image file to upload and set as profile header background
     * @param <Steps>                - method caller steps class type
     * @return - instance of Steps class that extends ProfileHeaderSteps class from which method was invoked
     */
    @Step("Upload new profile header background image. File path: '{backgroundImageFile}'.")
    public <Steps extends ProfileHeaderSteps<Page>> Steps uploadNewProfileHeaderBackground(
            Class<Steps> methodCallerStepsClass,
            File backgroundImageFile) {
        this.page.getEditHeaderBackgroundBtn().click();
        PopupModal popupModal = new PopupModal();
        popupModal.getFileUploadInput().uploadFile(backgroundImageFile);
        popupModal.getSaveBtn().click();
        popupModal.getAnimatedSaveBtn().shouldNotBe(exist, Duration.ofSeconds(10));
        return this.getStepsObjectInstance(methodCallerStepsClass);
    }
}