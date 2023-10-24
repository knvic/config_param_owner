package world.raketa.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import java.util.Set;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CareerPage {
    SelenideElement
            linkToThePageVacancy = $("[href=\"https://job.raketa.world/page28587673.html\"]");
    ElementsCollection
            loadingAreaPageCareer = $$("[class='t-card__col t-card__col_withoutbtn']");

    public CareerPage waitingForThePageCareerLoad() {
        loadingAreaPageCareer.findBy(text("Еще мы совместно участвуем в экологических проектах, занимаемся йогой и совершенствуем английский язык.")).shouldBe(visible, Duration.ofSeconds(10)).scrollTo();
        return this;
    }


    public CareerPage linkVacancyEnabled() {
        linkToThePageVacancy.shouldBe(enabled).scrollTo();
        return this;
    }
    public CareerPage goToListVacancy() {
        linkToThePageVacancy.shouldBe(enabled).scrollTo().click();
        return this;
    }

    public CareerPage selectTargetWindows() {
        Set<String> windowHandles = getWebDriver().getWindowHandles();
        int c = 1;
        String win1 = null;
        String win2 = null;
        for (String windowHandle : windowHandles) {
            //System.out.println("Window handle: " + windowHandle);
            if (c == 1) {
                win1 = windowHandle;
            } else {
                win2 = windowHandle;
            }
            c++;
        }
        switchTo().window(win1).close();
        Selenide.switchTo().window(win2);
        return this;
    }

}