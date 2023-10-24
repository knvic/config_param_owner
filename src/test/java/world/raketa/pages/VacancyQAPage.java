package world.raketa.pages;

import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class VacancyQAPage {

    ElementsCollection
            areaQAVacancy = $$(".t396");

    public VacancyQAPage yogaAtLunch() {
        areaQAVacancy.findBy(text(" йога в обед")).scrollTo();
        return this;
    }
}
