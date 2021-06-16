package models.containers;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static utils.Utils.getMatchedText;

@Getter
public class DeletedCartItemContainer {

    private final SelenideElement message;

    public DeletedCartItemContainer(SelenideElement message) {
        this.message = message;
    }

    public String getProductName() {
        return getMatchedText(this.message.getOwnText(), "(?<=удалили\\s)[^\\s].+(?=\\s)");
    }
}
