package pages.profile;

import com.codeborne.selenide.SelenideElement;
import enums.UrlPrefix;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PopupModal extends ProfileHeader {

    private static final String popupContainerCssLocator = ".popup-style__container_visible .profile-popup";

    public PopupModal() {
        super(UrlPrefix.DEFAULT, null);
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return By.cssSelector(popupContainerCssLocator);
    }

    public SelenideElement getFileUploadInput() {
        return $(getConcatenatedBySpaceCssLocator(popupContainerCssLocator, "input"));
    }

    public SelenideElement getSaveBtn() {
        return $(getConcatenatedBySpaceCssLocator(popupContainerCssLocator, ".button-style_primary"));
    }

    public SelenideElement getAnimatedSaveBtn() {
        return $(getConcatenatedBySpaceCssLocator(popupContainerCssLocator, ".button-style_animated"));
    }
}