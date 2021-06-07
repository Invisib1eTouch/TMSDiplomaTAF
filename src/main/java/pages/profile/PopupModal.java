package pages.profile;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class PopupModal extends ProfileHeader {

    private static final String popupContainerCssLocator = ".popup-style__container_visible .profile-popup";

    public PopupModal() {
        super(null);
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return By.cssSelector(popupContainerCssLocator);
    }

    public SelenideElement getFileUploadInput() {
        return $(makePopupElLocator("input"));
    }

    public SelenideElement getSaveBtn() {
        return $(makePopupElLocator(".button-style_primary"));
    }

    public SelenideElement getAnimatedSaveBtn() {
        return $(makePopupElLocator(".button-style_animated"));
    }

    private String makePopupElLocator(String locatorEnding) {
        return String.format("%s %s", popupContainerCssLocator, locatorEnding);
    }
}
