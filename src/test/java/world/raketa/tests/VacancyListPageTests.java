package world.raketa.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;

import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.ValueSource;
import world.raketa.pages.*;
import static io.qameta.allure.Allure.step;

@Epic("Ракета")
@Feature("Первоначальное тестирование")
@Owner("krivorotovnv")
@Link(value = "Testing", url = "https://raketa.world")
@Severity(SeverityLevel.BLOCKER)
public class VacancyListPageTests extends BaseTest {
    MainPage mainPage = new MainPage();
    CareerPage careerPage = new CareerPage();
    VacancyListPage vacancyListPage = new VacancyListPage();

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

}
