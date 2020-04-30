package stepdefs;

import cucumber.api.java.Before;
import cucumber.api.java.ru.Тогда;
import helpers.AllureHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sbtqa.tag.pagefactory.PageFactory;

import static helpers.RobotHelper.sendKeysCombo;

public class CommonSteps {

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
        AllureHelper.createAllureEnvironmentPropFile();
    }

    @Тогда("^ожидаем \"(\\d+)\" секунд$")
    public void waitTime(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000);
    }

    @Тогда("^кликает кнопку c названием \"([^\"]*)\"$")
    public void clickButtonByName(String button_name) {

        WebElement element = PageFactory.getWebDriver().findElement(By.xpath("//button[@aria-label=\"" + button_name + "\"]"));
        ((JavascriptExecutor) PageFactory.getWebDriver()).executeScript("arguments[0].click();", element);

    }

    @Тогда("^пользователь подтверждает действие на странице$")
    public void confirmAlert() {
        WebDriverWait webDriverWait = new WebDriverWait(PageFactory.getWebDriver(), 60);
        webDriverWait.until(ExpectedConditions.alertIsPresent());
        PageFactory.getWebDriver().switchTo().alert().accept();
    }

    @Тогда("^пользователь нажимает ctrl s$")
    public static void saveCtrlS() {
        String [] keys = {
                "VK_CONTROL", "VK_S"
        };
        sendKeysCombo(keys);
    }

}