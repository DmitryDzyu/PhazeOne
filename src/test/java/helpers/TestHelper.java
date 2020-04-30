package helpers;

import org.openqa.selenium.TimeoutException;
import ru.sbtqa.tag.qautils.properties.Props;

import java.util.Date;
import java.util.HashMap;

public class TestHelper {

    private static long initTime;
    private long currentTime;
    private static HashMap<String, String> lastJsonData = new HashMap<>();
    private static HashMap<String, String> testData;

    public static Long getDefaultTimeoutInSeconds() {
        return Long.valueOf(Props.get("page.load.timeout")) / 1000;
    }

    public static Long getDefaultTimeoutInMilliseconds() {
        return Long.valueOf(Props.get("page.load.timeout"));
    }

    public static void initTimeout() {
        Date date = new Date();
        initTime = date.getTime();
    }

    public static long getCurrentTime() {
        Date date = new Date();
        return date.getTime();
    }

    public static boolean isTimeout() {
        if (initTime < (getCurrentTime() - getDefaultTimeoutInMilliseconds())) {
            throw new TimeoutException(Constants.TIMEOUT_MESSAGE) ;
        } else {
            return false;
        }
    }

    public static HashMap<String, String> getLastJsonData() {
        return lastJsonData;
    }

    public static void setLastJsonData(HashMap<String, String> hashMap) {
        lastJsonData = hashMap;
    }

    public static String createStringUnicalValue(int length, String prefix) {
        return prefix + RandomDataHelper.randomString(RandomDataHelper.randomType.RUS, length);
    }

    public static String createIntUnicalValue(int length, String prefix) {
        return prefix + RandomDataHelper.randomString(RandomDataHelper.randomType.NUM, length);
    }

    public static HashMap<String, String> getTestData() {
        if(TestHelper.testData == null) {
            TestHelper.testData = new HashMap<>();
        }
        return testData;
    }

    public static void setTestData(String key, String value) {
        getTestData().put(key, value);
    }


    public static String getTestDataValue(String key) {
        String value = "";
        if(isContainsInTestData(key)) {
            return getTestData().get(key);
        }
        return value;
    }

    public static boolean isContainsInTestData(String key) {
        return getTestData().containsKey(key);
    }
}