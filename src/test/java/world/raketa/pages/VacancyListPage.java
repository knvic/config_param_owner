package world.raketa.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;


public class VacancyListPage {
    SelenideElement
            areaOfVacancy = $("#allrecords");

    public VacancyListPage findVacancy(String vacancy) {
        areaOfVacancy.shouldHave(text(vacancy));
        return this;
    }
}
