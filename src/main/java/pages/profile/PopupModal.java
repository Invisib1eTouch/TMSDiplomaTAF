package pages.profile;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class PopupModal extends ProfileHeader {

    public static final String popupContainerCssLocator = ".popup-style__container_visible .profile-popup";

    public PopupModal() {
        super(null);
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return By.cssSelector(popupContainerCssLocator);
    }

    public SelenideElement getFileUploadInput(){
        return $(popupContainerCssLocator + " input");
    }
}
