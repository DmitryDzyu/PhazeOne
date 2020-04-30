package stepdefs;

import cucumber.api.java.ru.Тогда;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbtqa.tag.pagefactory.PageFactory;

public class ParametersSteps {

    private Logger logger = LoggerFactory.getLogger(ParametersSteps.class);

    @Тогда("^открываем \"([^\"]*)\" страницу$")
    public void openUrlFromJvmParameters(String autotest_target_url) {
        String testUrl = System.getProperty(autotest_target_url);
        logger.info("autotest_target_url=" + testUrl);
        PageFactory.getWebDriver().navigate().to(testUrl);
    }
}