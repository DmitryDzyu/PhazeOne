import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        glue = {"ru.sbtqa.tag.stepdefs.ru", "stepdefs"},
        features = {"src/test/resources/features/"},
        plugin = {"pretty", "ru.sbtqa.tag.pagefactory.support.TagPFAllureReporter", "cucumber.runtime.formatter.HTMLFormatter:output"}
)

public class CucumberTest {
}