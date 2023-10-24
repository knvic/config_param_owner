package world.raketa.tests;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import world.raketa.pages.*;
import static io.qameta.allure.Allure.step;

@Epic("Ракета")
@Feature("Первоначальное тестирование")
@Owner("krivorotovnv")
@Link(value = "Testing", url = "https://raketa.world")
@Severity(SeverityLevel.BLOCKER)
public class CareerPageTests extends BaseTest {
    MainPage mainPage = new MainPage();
    CareerPage careerPage = new CareerPage();

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

}
