package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class ProfileMenu extends CommonHeader {
    private static final By userIdBy = By.cssSelector(".b-top-profile__name a");

    public ProfileMenu() {
        super(null);
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return userIdBy;
    }

    public SelenideElement getUserId(){
        return $(userIdBy);
    }
}
