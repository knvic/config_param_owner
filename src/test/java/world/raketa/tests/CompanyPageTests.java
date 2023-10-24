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
public class CompanyPageTests extends BaseTest {
    MainPage mainPage = new MainPage();
    CompanyPage companyPage = new CompanyPage();


    @Story("Заполнение формы обратной связи")
    @DisplayName("Проверка выбора меню КОПМАНИЯ, вызова окна для ввода данных для связи, возможности ввода данных.")
    @Tags({
            @Tag("raketa")
    })
    @Test
    void checkingFeedbackForm() {
        step("Открытие сайта", () -> {
            mainPage
                    .openPage();
        });
        step("Ожидаем корректной загрузки стартовой страницы (появление определенного текста) ", () -> {
            mainPage
                    .waitingForTheSiteToLoad();
        });

        step("Находим пункт меню КОМПАНИЯ и кликаем на него", () -> {
            mainPage
                    .goToTheMenuItemCompany();
        });
        step("Ожидаем корректной загрузки страницы Компания и наличия в нем определенного текста ", () -> {
            companyPage
                    .waitingForTheSiteCompanyToLoad();
        });

        step("Нажимаем на кнопку вызова окна ввода данных для связи ", () -> {
            companyPage
                    .callingInpitTab();
        });

        step("Проверяем загрузку и видимость окна", () -> {
            companyPage
                    .checkVisibleInpitTab();
        });

        step("Заполняем поля формы. Поле email вводим не правильно, чтобы форма не отправлялась и жмем отправить", () -> {
            companyPage
                    .fillingTheForm();
        });

        step("Так как заполнено не корректно, проверяем, что форма видна ", () -> {
            companyPage
                    .checkVisibleInpitTab();
        });

    }

}
