package world.raketa.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import world.raketa.pages.*;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Epic("Ракета")
@Feature("Первоначальное тестирование")
@Owner("krivorotovnv")
@Link(value = "Testing", url = "https://raketa.world")
@Severity(SeverityLevel.BLOCKER)
public class VacancyQAPageTests extends BaseTest {
       VacancyQAPage vacancyQAPage = new VacancyQAPage();

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
