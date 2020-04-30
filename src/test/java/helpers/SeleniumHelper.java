package helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.qautils.properties.Props;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class SeleniumHelper {

    private static Logger log = LoggerFactory.getLogger(SeleniumHelper.class);

    public static void unfocus() {
        JavascriptExecutor js = (JavascriptExecutor) PageFactory.getDriver();
        js.executeScript("document.activeElement.blur(); return true");
    }

    public static void scrollFocusClickJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) PageFactory.getDriver();
        executor.executeScript("arguments[0].scrollIntoView();", element);
        executor.executeScript("arguments[0].focus();", element);
        executor.executeScript("arguments[0].click();", element);
        log.info("successful click on element via JavaScript");
    }

    public static boolean resendKeys(WebElement edit, String value, Boolean focus) {
        edit.sendKeys(Keys.CONTROL + "a");
        edit.sendKeys(value);
        if(focus) {
            unfocus();
        }
        return true;
    }

    public static void hoverByElement(WebElement element) {
        PageFactory.getActions().moveToElement(element).perform();
        //из-за низкой скорости сайта меню элемента может не успеть раскрыться и будет ElementNotIntractableException
//        Thread.sleep(Constants.WAIT);
    }

    private static void copyToBufferRemote(String text) {
        String clipboardUrl = getSelenoidUtilURL("clipboard");
        log.debug("send request to clipboard");
        Response resp = RestAssured.given().body(text).header("Content-Type", ContentType.URLENC + "; charset=utf-8").post(clipboardUrl);
        log.debug("response status: {}", resp.getStatusLine());
        log.debug("clipboard message: \n" + RestAssured.given().get(clipboardUrl).asString());
    }

    private static void copyToBufferLocal(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public static void copyToClipboard(String text) {
        if (PageFactory.getWebDriver().getClass().equals(RemoteWebDriver.class)) {
            SeleniumHelper.copyToBufferRemote(text);
        } else {
            SeleniumHelper.copyToBufferLocal(text);
        }
    }

    private static String getSelenoidUtilURL(String suffix) {
        String selenoid = Props.get("webdriver.url").replace("wd/hub", "");
        String sessionId = ((RemoteWebDriver) PageFactory.getWebDriver()).getSessionId().toString();
        String urlWithSuffix = selenoid + suffix + "/" + sessionId;
        log.debug("url for selenoid interactions {}",urlWithSuffix);
        return urlWithSuffix;
    }

    public static boolean isFileDownload(String fileName) {
        String targetUrl = getSelenoidUtilURL("download");
        Response resp = RestAssured.given().get(targetUrl + "/" + fileName);
        return resp.statusCode() == 200;
    }
}