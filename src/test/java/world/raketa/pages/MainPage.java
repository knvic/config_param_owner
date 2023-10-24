package world.raketa.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import java.util.List;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class MainPage {
    SelenideElement
            topMenu = $(".t199__holder"),
            selectLocale = $(".box_lang"),
            vacancy = $("[href='https://job.raketa.world/page28587673.html']"),
            m_navigation = $("[aria-label='Основная навигация']"),
            linkToThePageCareer = $("[href='/career']");
    ElementsCollection
            pullDownElement = $$(".t199__holder li"),
            loadingAreaMainPage = $$("[data-hook-content=covercontent]"),
            listPullDownMenu = $$(".t-menusub");

    public MainPage openPage() {
        open(baseUrl);
        return this;
    }

    public MainPage setLocale(Locale locale) {
        selectLocale.hover().find(byText(locale.getLanguage())).click();
        return this;
    }


    public MainPage shouldHaveTargetMenu(List<String> list) {
        for (String item : list
        ) {
            m_navigation.should(text(item));
        }
        return this;
    }

    public MainPage checkElementsPullDownMenu(List<String> list, int count, String first) {

        for (String item : list) {

            if (count == 0) {

                pullDownElement.first().hover().shouldHave(text(item)).shouldBe(visible, Duration.ofSeconds(10));
            } else {

                pullDownElement.first().sibling(count - 1).shouldHave(text(first)).hover().shouldHave(text(item)).shouldBe(visible);

            }

        }

        return this;
    }


    public MainPage career() {
        pullDownElement.first().hover();
        listPullDownMenu.first().hover().shouldHave(text("Карьера")).shouldBe(visible, Duration.ofSeconds(10));
        linkToThePageCareer.click();
        return this;
    }


    public MainPage waitingForTheSiteToLoad() {
        loadingAreaMainPage.first().shouldHave(text("Экономьте на командировках, улучшайте контроль по поездкам, " +
                "ускоряйте отчетность. Пришло время рассмотреть цифровую " +
                "платформу Ракета"), Duration.ofSeconds(10));

        return this;
    }

    public MainPage goToTheMenuItemCompany() {
        topMenu.find(byText("Компания")).click();
        return this;
    }










}
