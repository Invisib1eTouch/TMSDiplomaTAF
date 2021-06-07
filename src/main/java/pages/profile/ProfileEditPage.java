package pages.profile;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProfileEditPage extends BasePage {

    private static final By saveBtnBy = By.className("auth-button_primary");
    private static final By nickInputBy = By.xpath("//div[normalize-space()='Ник']" +
            "/ancestor::div[contains(@class, 'auth-form__row')]//input");
    private static final By lastNameInputBy = By.cssSelector("input[placeholder='Фамилия']");
    private static final By firstNameInputBy = By.cssSelector("input[placeholder='Имя']");
    private static final By patronymicInputBy = By.cssSelector("input[placeholder='Отчество']");
    private static final By dateOfBirthInputBy = By.xpath("//div[normalize-space()='Дата рождения']" +
            "/ancestor::div[contains(@class, 'auth-form__row')]//input[1]");
    private static final By yearOfBirthInputBy = By.xpath("//div[normalize-space()='Дата рождения']" +
            "/ancestor::div[contains(@class, 'auth-form__row')]//input[2]");
    private static final By cityInputBy = By.xpath("//div[normalize-space()='Из города']" +
            "/ancestor::div[contains(@class, 'auth-form__row')]//input");
    private static final By myTechInputBy = By.xpath("//div[normalize-space()='Моя техника']" +
            "/ancestor::div[contains(@class, 'auth-form__row')]/descendant::textarea");
    private static final By occupationInputBy = By.xpath("//div[normalize-space()='Род занятий']" +
            "/ancestor::div[contains(@class, 'auth-form__row')]/descendant::textarea");
    private static final By interestsInputBy = By.xpath("//div[normalize-space()='Интересы']" +
            "/ancestor::div[contains(@class, 'auth-form__row')]/descendant::textarea");
    private static final By forumCaptionInputBy = By.xpath("//div[normalize-space()='Подпись на Форуме']" +
            "/ancestor::div[contains(@class, 'auth-form__row')]/descendant::textarea");


    public ProfileEditPage() {
        super(UrlPrefix.CATALOG_PREFIX, null);
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return saveBtnBy;
    }

    public SelenideElement getSaveBtn() {
        return $(saveBtnBy);
    }

    public SelenideElement getNickInput() {
        return $(nickInputBy);
    }

    public SelenideElement getLastNameInput() {
        return $(lastNameInputBy);
    }

    public SelenideElement getFirstNameInput() {
        return $(firstNameInputBy);
    }

    public SelenideElement getPatronymicInput() {
        return $(patronymicInputBy);
    }

    public SelenideElement getDateOfBirthInput() {
        return $(dateOfBirthInputBy);
    }

    public SelenideElement getYearOfBirthInput() {
        return $(yearOfBirthInputBy);
    }

    public SelenideElement getCityInput() {
        return $(cityInputBy);
    }

    public SelenideElement getMyTechInput() {
        return $(myTechInputBy);
    }

    public SelenideElement getOccupationInput() {
        return $(occupationInputBy);
    }

    public SelenideElement getInterestsInput() {
        return $(interestsInputBy);
    }

    public SelenideElement getForumCaptionInput() {
        return $(forumCaptionInputBy);
    }

}
