package world.raketa.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import world.raketa.pages.Locale;
import world.raketa.pages.MainPage;
import world.raketa.utils.DataGenerationUtils;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RaketaWorldTests extends RaketaRemoteBaseTest {
    MainPage raketaWorldPage = new MainPage();
    DataGenerationUtils dataGenerationUtils = new DataGenerationUtils();
    String
            firstName = dataGenerationUtils.getFirstName(),
            lastName = dataGenerationUtils.getLastName(),
            phone = dataGenerationUtils.getUserNumber();
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
    }

    static Stream<Arguments> checkingСompositionMenu() {
        return Stream.of(
                Arguments.of("КОМПАНИЯ", List.of("О компании", "Команда", "Медиацентр", "Карьера", "Контакты")),
                Arguments.of("ПРОДУКТЫ", List.of("Цифровая платформа для командировок", "Мобильное приложение для бизнес-путешественников", "Управление персональными и корпоративными данными", "Управление расходами", "Автопарк")),
                Arguments.of("КЛИЕНТАМ", List.of("Бизнесу", "Государственным компаниям", "Интеграции", "Безопасность", "Получить межгалактический паспорт"))

        );
    }

    @Epic("Ракета")
    @Feature("Первоначальное тестирование")
    @Story("Соответствие элементов верхнего меню")
    @Owner("krivorotovnv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://raketa.world")
    @DisplayName("Параметризованный тест проверки списка элементов выпадаюзешл меню при наведении на элементы основного меню.")
    @MethodSource("checkingСompositionMenu")
    @Tag("raketa")
    @ParameterizedTest(name = "Проверка наличия выпадающего списка элементов. при наведениина пункт меню  {0} отображается элементы списка {1}")
    void checkingСompositionMenu(String item, List<String> list) {
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


    }

    @ValueSource(
            strings = {
                    "QA automation engineer",
                    "QA Lead"
            }
    )
    @Epic("Ракета")
    @Feature("Первоначальное тестирование")
    @Story("Проверка наличия вакансий на странице вакансий")
    @Owner("krivorotovnv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://raketa.world")
    @DisplayName("Тест проверки ссылки на страницу вакансий и вакансий на странице согласно списка")

    @Tags({
            @Tag("raketa")

    })
    @ParameterizedTest(name = "Проверка наличия вакансии  =>  {0}")
    void availabilityLinkToVacanciesAndListVacancy(String vacancy) {
        step("Открытие сайта", () -> {
            raketaWorldPage
                    .openPage();
        });
        step("Ожидаем корректной загрузки стартовой страницы (появление определенного текста) ", () -> {
            raketaWorldPage
                    .waitingForTheSiteToLoad();
        });

        step("первый пункт основного меню. В выпадаюзм списке находим Карьера, проверяем видимость и выбираем", () -> {
            raketaWorldPage
                    .career();
        });
        step("Ожидаем корректной загрузки страницы Карьера (появление определенного текста) ", () -> {
            raketaWorldPage
                    .waitingForThePageCareerLoad();
        });

        step("Проверка наличия ссылки на вакансии", () -> {
            raketaWorldPage
                    .linkVacancyEnabled();
        });

        step("После оперехода по ссылке на страницу вакансии открывается новое окно, а фокус оствется на другом. Ищем windowHandle окон, закрываем не нужное. переходим на окнос вакансиями", () -> {
            raketaWorldPage
                    .selectTargetWindows();
        });

        step("Проверка наличия ссылки на вакансии QA automation engineer);", () -> {
            raketaWorldPage
                    .findVacancy(vacancy);
        });


    }

    @Epic("Ракета")
    @Feature("Первоначальное тестирование")
    @Story("Проверка наличия главного условия в работе QA automation engineer")
    @Owner("krivorotovnv")
    @Severity(SeverityLevel.BLOCKER)
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
            raketaWorldPage
                    .yogaAtLunch();
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
            @Tag("raketa")
                })
    @Test
    void checkingFeedbackForm() {
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
                    .fillingTheForm(firstName, "Не правильный email", phone, "Компания");
        });

        step("Так как заполнено не корректно, проверяем, что форма видна ", () -> {
            raketaWorldPage
                    .checkVisibleInpitTab();
        });


    }

}
