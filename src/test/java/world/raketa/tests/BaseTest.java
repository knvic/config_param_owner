package world.raketa.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import world.raketa.config.ConfigReader;
import world.raketa.config.WebConfig;
import world.raketa.config.WebConfigForProject;
import world.raketa.helpers.Attach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static world.raketa.config.ConfigReader.webConfig;

public class BaseTest {
    private static final WebConfig config = ConfigReader.Instance.read();

    @BeforeAll
    static void beforeAll() {
        WebConfigForProject webConfigForProject = new WebConfigForProject(config);
        webConfigForProject.webConfig();

    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {

        if (webConfig.isRemote()) {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
}
        closeWebDriver();
    }



}
