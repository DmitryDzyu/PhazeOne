package stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import helpers.AllureHelper;
import ru.sbtqa.tag.stepdefs.SetupSteps;


public class Hooks extends SetupSteps {

    @Before
    public void setUp(Scenario scenario) {
        AllureHelper.createAllureEnvironmentPropFile();
        super.setUp(scenario);
    }

    @After
    public void alertCheck() {
        super.tearDown();
    }
}
