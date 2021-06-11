package pages.profile;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class PopupModal extends ProfileHeader {

    private static final String popupContainerCssLocator = ".popup-style__container_visible .profile-popup";

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return By.cssSelector(popupContainerCssLocator);
    }

    public SelenideElement getFileUploadInput() {
        return $(getPopupElLocator("input"));
    }

    public SelenideElement getSaveBtn() {
        return $(getPopupElLocator(".button-style_primary"));
    }

    public SelenideElement getAnimatedSaveBtn() {
        return $(getPopupElLocator(".button-style_animated"));
    }

    /**
     * @param locatorEnding - popup element locator inside popup container
     * @return - string popup full locator
     */
    private String getPopupElLocator(String locatorEnding) {
        return String.format("%s %s", popupContainerCssLocator, locatorEnding);
    }
}