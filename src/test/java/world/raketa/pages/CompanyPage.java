package world.raketa.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import world.raketa.utils.DataGenerationUtils;
import java.time.Duration;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CompanyPage {

SelenideElement
    button = $("[href='#popup:form_ru']"),
        tabInput = $(".t702__wrapper"),
        fieldName = tabInput.$("[name=Name]"),
        fieldEmail = tabInput.$("[name=Email]"),
        fieldPhone = tabInput.$("[name=Phone]"),
        fieldInput = tabInput.$("[name=Input]"),
        fieldCheckBox = tabInput.$(".t-checkbox__indicator"),
        buttonSubmit = tabInput.$("[type=submit]");

    ElementsCollection
            fieldText = $$("div [field=text]");

    public CompanyPage waitingForTheSiteCompanyToLoad() {
        fieldText.first().shouldHave(text("Компания Ракета – российский разработчик"));
        return this;
    }
    public CompanyPage callingInputTab() {
        button.scrollTo().click();
        return this;
    }

    public CompanyPage checkVisibleInputTab() {
        tabInput.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }
    public CompanyPage fillingTheForm() {

        DataGenerationUtils dataGenerationUtils = new DataGenerationUtils();

        fieldName.setValue(dataGenerationUtils.getFirstName());
        fieldEmail.setValue("Не правильный email");
        fieldPhone.setValue(dataGenerationUtils.getUserNumber());
        fieldInput.setValue("Компания");
        fieldCheckBox.scrollTo().click();
        buttonSubmit.click();
        return this;
    }
}
