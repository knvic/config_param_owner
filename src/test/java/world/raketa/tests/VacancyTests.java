package world.raketa.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import world.raketa.pages.CareerPage;
import world.raketa.pages.MainPage;
import world.raketa.pages.VacancyListPage;
import world.raketa.pages.VacancyQAPage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Epic("Ракета")
@Feature("Первоначальное тестирование")
@Owner("krivorotovnv")
@Link(value = "Testing", url = "https://raketa.world")
@Severity(SeverityLevel.BLOCKER)
public class VacancyTests extends BaseTest {
    MainPage mainPage = new MainPage();
    CareerPage careerPage = new CareerPage();
    VacancyListPage vacancyListPage = new VacancyListPage();

    VacancyQAPage vacancyQAPage = new VacancyQAPage();

    @ValueSource(
            strings = {
                    "QA automation engineer",
                    "QA Lead"
            }
    )
    @Story("Проверка наличия вакансий на странице вакансий")
    @DisplayName("Тест проверки ссылки на страницу вакансий и вакансий на странице согласно списка")
    @Tags({
            @Tag("raketa")
    })
    @ParameterizedTest(name = "Проверка наличия вакансии  =>  {0}")
    void availabilityLinkToVacanciesAndListVacancy(String vacancy) {
        step("Открытие сайта", () -> {
            mainPage
                    .openPage();
        });
        step("Ожидаем корректной загрузки стартовой страницы (появление определенного текста) ", () -> {
            mainPage
                    .waitingForTheSiteToLoad();
        });

        step("Первый пункт основного меню. В выпадающем списке находим Карьера, проверяем видимость и выбираем", () -> {
            mainPage
                    .career();
        });
        step("Ожидаем корректной загрузки страницы Карьера (появление определенного текста) ", () -> {
            careerPage
                    .waitingForThePageCareerLoad();
        });

        step("Проверка доступности ссылки на страницу Вакансии и переход на нее", () -> {
            careerPage
                    .goToListVacancy();
        });

        step("После перехода по ссылке на страницу вакансии открывается новое окно, а фокус оствется на другом. Ищем windowHandle окон, закрываем не нужное, переходим на окно с вакансиями", () -> {
            careerPage
                    .selectTargetWindows();
        });

        step("Проверка наличия ссылки на вакансии QA automation engineer);", () -> {
            vacancyListPage
                    .findVacancy(vacancy);
        });

    }

    @Story("Проверка ссылкки на страницу вакансий")
    @DisplayName("Тест наличия и доступности ссылки на страницу вакансий со страницы Карьера")
    @Tags({
            @Tag("raketa")
    })
    @Test
    void availabilityLinkToVacanciesAndListVacancy() {
        step("Открытие сайта", () -> {
            mainPage
                    .openPage();
        });
        step("Ожидаем корректной загрузки стартовой страницы (появление определенного текста) ", () -> {
            mainPage
                    .waitingForTheSiteToLoad();
        });

        step("Первый пункт основного меню. В выпадающем списке находим Карьера, проверяем видимость и выбираем", () -> {
            mainPage
                    .career();
        });
        step("Ожидаем корректной загрузки страницы Карьера (появление определенного текста) ", () -> {
            careerPage
                    .waitingForThePageCareerLoad();
        });

        step("Проверка наличия и доступности ссылки на страницу Вакансии", () -> {
            careerPage
                    .linkVacancyEnabled();
        });

    }

    @Story("Проверка наличия главного условия в работе QA automation engineer")
    @Link(value = "Testing", url = "https://job.raketa.world/qaautomation")
    @DisplayName("Тестирование наличия опции Йога в обед в условиях работы")
    @Tags({
            @Tag("raketa"),
            @Tag("smoke"),

    })
    @Test
    void mainConditionOfWork() {
        step("Открытие сайта на странице карьера", () -> {
            open("https://job.raketa.world/qaautomation");
        });

        step("Поиск ключевого условия Йога в обед в предложениях вакансии", () -> {
            vacancyQAPage
                    .yogaAtLunch();
        });

    }

}
