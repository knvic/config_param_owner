package world.raketa.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class MainPage {


    SelenideElement
            topMenu = $(".t199__holder"),
            selectLocale = $(".box_lang"),
            vacancy = $("[href='https://job.raketa.world/page28587673.html']"),
            button = $("[href='#popup:form_ru']"),
            m_navigation = $("[aria-label='Основная навигация']"),
            tabInput = $(".t702__wrapper"),
            fieldName = tabInput.$("[name=Name]"),
            fieldEmail = tabInput.$("[name=Email]"),
            fildPhone = tabInput.$("[name=Phone]"),
            fildInput = tabInput.$("[name=Input]"),
            fieldCheckBox = tabInput.$(".t-checkbox__indicator"),
            buttonSubmit = tabInput.$("[type=submit]"),
            linkToThePageCareer = $("[href='/career']"),
            linkToThePageVacancy = $("[href=\"https://job.raketa.world/page28587673.html\"]"),
            areaOfVacancy = $("#allrecords");


    ElementsCollection
            navigation = $$("[aria-label='Основная навигация']"),
            pullDownElement = $$(".t199__holder li"),
            loadingAreaMainPage = $$("[data-hook-content=covercontent]"),
            fieldText = $$("div [field=text]"),
            listPullDownMenu = $$(".t-menusub"),
            loadingAreaPageCareer = $$("[class='t-card__col t-card__col_withoutbtn']"),
            areaQAVacancy = $$(".t396");
    ;


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


    public MainPage waitingForThePageCareerLoad() {
        loadingAreaPageCareer.findBy(text("Еще мы совместно участвуем в экологических проектах, занимаемся йогой и совершенствуем английский язык.")).shouldBe(visible, Duration.ofSeconds(10)).scrollTo();
        return this;
    }


    public MainPage linkVacancyEnabled() {
        linkToThePageVacancy.shouldBe(enabled).scrollTo().click();
        return this;
    }


    public MainPage selectTargetWindows() {
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


    public MainPage findVacancy(String vacancy) {
        areaOfVacancy.shouldHave(text(vacancy));
        return this;
    }

    public MainPage yogaAtLunch() {
        areaQAVacancy.findBy(text(" йога в обед")).scrollTo();
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

    public MainPage waitingForTheSiteCompanyToLoad() {
        fieldText.first().shouldHave(text("Компания Ракета – российский разработчик"));
        return this;
    }

    public MainPage callingInpitTab() {
        button.scrollTo().click();
        return this;
    }


    public MainPage checkVisibleInpitTab() {
        tabInput.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }

    public MainPage fillingTheForm(String name, String email, String phone, String input) {
        fieldName.setValue(name);
        fieldEmail.setValue(email);
        fildPhone.setValue(phone);
        fildInput.setValue(input);
        fieldCheckBox.scrollTo().click();
        buttonSubmit.click();
        return this;
    }


}
