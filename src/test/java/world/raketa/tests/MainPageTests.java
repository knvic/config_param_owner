package world.raketa.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import world.raketa.pages.*;
import java.util.List;
import java.util.stream.Stream;
import static io.qameta.allure.Allure.step;

@Epic("Ракета")
@Feature("Первоначальное тестирование")
@Owner("krivorotovnv")
@Link(value = "Testing", url = "https://raketa.world")
@Severity(SeverityLevel.BLOCKER)
public class MainPageTests extends BaseTest {
    MainPage mainPage = new MainPage();
    private static int count = 0;


    static Stream<Arguments> changeLocaleTest() {
        return Stream.of(
                Arguments.of(Locale.RU, List.of("КОМПАНИЯ", "ПРОДУКТЫ", "КЛИЕНТАМ", "СТАТЬ КЛИЕНТОМ", "КОНТАКТЫ", "ВОЙТИ")),
                Arguments.of(Locale.EN, List.of("COMPANY", "PRODUCTS", "FOR CLIENTS", "GET STARTED", "CONTACTS", "LOGIN"))
        );
    }
    @Story("Переключение локали")
    @DisplayName("Параметризованный тест проверки наличия списка основного меню в разных локалях")
    @MethodSource("changeLocaleTest")
    @Tag("raketa")
    @ParameterizedTest(name = "Проверка меню сайта при перелючении локали на {0} отображается меню {1}")
    void changeLocaleTest(Locale locale, List<String> list) {
        step("Открытие сайта", () -> {
            mainPage
                    .openPage();
        });

        step("Выбор локали {0}", () -> {
            mainPage
                    .setLocale(locale);

        });

        step("Проверка наличия элементов меню согласно списка, а,так же, соответствия с выбранным языком", () -> {
            mainPage
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
    @Story("Соответствие элементов верхнего меню")
    @DisplayName("Параметризованный тест проверки списка элементов выпадающего меню при наведении на элементы основного меню.")
    @MethodSource("checkingСompositionMenu")
    @Tag("raketa")
    @ParameterizedTest(name = "Проверка наличия выпадающего списка элементов. При наведениина пункт меню  {0} отображается элементы списка {1}")
    void checkingСompositionMenu(String item, List<String> list) {
        step("Открытие сайта", () -> {
            mainPage
                    .openPage();
        });

        step("Ожидаем корректной загрузки стартовой страницы (появление определенного текста) ", () -> {
            mainPage
                    .waitingForTheSiteToLoad();
        });

        step("Проверка элементов выпадающего меню соответствию списка.", () -> {
            mainPage
                    .checkElementsPullDownMenu(list, count, item);
        });
        count++;
    }

}
