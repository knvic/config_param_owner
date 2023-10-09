package world.raketa.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import world.raketa.pages.Locale;
import world.raketa.pages.RaketaWorldPage;
import world.raketa.utils.DataGenerationUtils;
import world.raketa.utils.RaketaBaseTest;
import world.raketa.utils.RaketaRemoteBaseTest;


import java.util.List;

import java.util.stream.Stream;


import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RaketaWorldTests extends RaketaBaseTest {
    RaketaWorldPage raketaWorldPage = new RaketaWorldPage();
    DataGenerationUtils dataGenerationUtils = new DataGenerationUtils();
    String
            firstName = dataGenerationUtils.getFirstName(),
            lastName = dataGenerationUtils.getLastName(),
            userNumber = dataGenerationUtils.getUserNumber();
    private static int count = 0;


    static Stream<Arguments> changeLocaleTest() {
        return Stream.of(
                Arguments.of(Locale.RU, List.of("КОМПАНИЯ", "ПРОДУКТЫ", "КЛИЕНТАМ", "СТАТЬ КЛИЕНТОМ", "КОНТАКТЫ", "ВОЙТИ")),
                Arguments.of(Locale.EN, List.of("COMPANY", "PRODUCTS", "FOR CLIENTS", "GET STARTED", "CONTACTS", "LOGIN"))
        );
    }

    @Epic("Ракета")
    @Feature("Первоначальное тестирование")
    @Story("Переключение локали")
    @Owner("krivorotovnv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://raketa.world")
    @DisplayName("Параметризованный тест проверки наличия списка основного меню в разных локалях")
    @MethodSource("changeLocaleTest")
    @Tag("raketa")
    @ParameterizedTest(name = "Проверка меню сайта при перелючении локали на {0} отображается меню {1}")
    void changeLocaleTest(Locale locale, List<String> list) {
        step("Открытие сайта", () -> {
            raketaWorldPage
                    .openPage();
        });

        step("Выбор локали {0}", () -> {
            raketaWorldPage
                    .setLocale(locale);

        });

        step("Проверка наличия элементов меню согласно списка, а ,так же, соответствия с выбранным языком", () -> {
            raketaWorldPage
                    .shouldHaveTargetMenu(list);
        });
        //Selenide.closeWindow();

    }

    static Stream<Arguments> parameterize2() {
        return Stream.of(
                Arguments.of("КОМПАНИЯ", List.of("О компании", "Команда", "Медиацентр", "Карьера", "Контакты")),
                Arguments.of("ПРОДУКТЫ", List.of("Цифровая платформа для командировок", "Мобильное приложение для бизнес-путешественников", "Управление персональными и корпоративными данными", "Управление расходами", "Автопарк")),
                Arguments.of("КЛИЕНТАМ", List.of("Бизнесу", "Государственным компаниям", "Интеграции", "Безопасность", "Получить межгалактический паспорт"))

        );
    }

    @Epic("Ракета")
    @Feature("Первоначальное тестирование")
    @Story("Соответствие путктов меню")
    @Owner("krivorotovnv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://raketa.world")
    @DisplayName("Параметризованный тест проверки списка элементов выпадаюзешл меню при наведении на элементы основного меню.")
    @MethodSource("parameterize2")
    @Tag("raketa")

    @ParameterizedTest(name = "Проверка наличия выпадающего списка элементов. при наведениина пункт меню  {0} отображается элементы списка {1}")
    void parameterize2(String item, List<String> list) {
        step("Открытие сайта", () -> {
            raketaWorldPage
                    .openPage();
        });

        step("Ожидаем корректной загрузки стартовой страницы (появление определенного текста) ", () -> {
            raketaWorldPage
                    .waitingForTheSiteToLoad();
        });

        step("Проверка элементов выпадающего меню соотсетствию списка.", () -> {
            raketaWorldPage
                    .checkElementsPullDownMenu(list, count, item);
        });
        count++;

        //Selenide.closeWindow();
        // Selenide.refresh();
    }


    @Epic("Ракета")
    @Feature("Первоначальное тестирование")
    @Story("Проверка наличия ссылки на страницу вакансий")
    @Owner("krivorotovnv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://raketa.world")
    @DisplayName("career Тест проверки ссылки на страницу вакансий со страницы карьера..")

    @Tags({
            @Tag("raketa"),
            @Tag("remote"),
            @Tag("local_test")
    })
    @Test
    void yoga() {
        step("Открытие сайта", () -> {
            raketaWorldPage
                    .openPage();
        });
        step("Ожидаем корректной загрузки стартовой страницы (появление определенного текста) ", () -> {
            raketaWorldPage
                    .waitingForTheSiteToLoad();
        });

        step("Переход на страницу Карьера", () -> {
            raketaWorldPage
                    .career();
        });
        step("Проверка наличия ссылки на вакансии", () -> {
            raketaWorldPage
                    .vacancyQA();
        });


    }

    @Epic("Ракета")
    @Feature("Первоначальное тестирование")
    @Story("Проверка наличия главного условия в работе")
    @Owner("krivorotovnv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://job.raketa.world/qaautomation")
    @DisplayName("Тестирование наличия опции Йога в обед в условиях работы")
    @Tag("raketa")
    @Test
    void yoga1() {
        step("Открытие сайта на странице карьера", () -> {
            open("https://job.raketa.world/qaautomation");
        });

        step("Поиск коючевого слова Йога в обед в условиях работы", () -> {
            raketaWorldPage
                    .yoga();
        });


    }

    @Epic("Ракета")
    @Feature("Первоначальное тестирование")
    @Story("")
    @Owner("krivorotovnv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://raketa.world")
    @DisplayName("Проверка выбора меню КОПМАНИЯ, вызова окна для ввода данных для связи, возможности ввода данных.")
    @Tags({
            @Tag("raketa"),
            @Tag("remote")
    })
    @Test
    void form() {
        step("Открытие сайта", () -> {
            raketaWorldPage
                    .openPage();
        });
        step("Ожидаем корректной загрузки стартовой страницы (появление определенного текста) ", () -> {
            raketaWorldPage
                    .waitingForTheSiteToLoad();
        });

        step("Находим пункт меню КОМПАНИЯ и кликаем на него", () -> {
            raketaWorldPage
                    .goToTheMenuItemCompany();
        });
        step("Ожидаем корректной загрузки страницы Компания и наличия в нем определенного текста ", () -> {
            raketaWorldPage
                    .waitingForTheSiteCompanyToLoad();
        });

        step("Нажимаем на кнопку вызова окна ввода данных ля связи ", () -> {
            raketaWorldPage
                    .callingInpitTab();
        });

        step("Проверяем загрузку и видимость окна", () -> {
            raketaWorldPage
                    .checkVisibleInpitTab();
        });

        step("Заполняем поля формы. Поле email вводим не правильно, чтобы форма не отправлялась и жмем отправить", () -> {
            raketaWorldPage
                    .fillingTheForm(firstName + " " + lastName, "Не правильный емайл", userNumber, "Тест");
        });

        step("Так как заполнено не корректно, проверяем, что форма видна ", () -> {
            raketaWorldPage
                    .checkVisibleInpitTab();
        });


    }

}