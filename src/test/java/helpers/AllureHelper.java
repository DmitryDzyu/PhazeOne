package helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * Класс для работы Allure отчёта
 */
public class AllureHelper {

    /**
     * Формирование файла environment.properties для Allure отчёта
     */
    public static void createAllureEnvironmentPropFile() {
        String allureEnvironment = System.getProperty("autotest_gitlab_env");

        if (!(allureEnvironment == null || allureEnvironment.isEmpty())) {
            FileOutputStream fileOutputStream = null;
            try {
                Properties properties = new Properties();
                String path = "target/allure-results";
                File allure = new File(path);
                if (!allure.exists()) {
                    allure.mkdir();
                }
                fileOutputStream = new FileOutputStream(path + "/environment.properties");

                Stream.of(allureEnvironment.split(","))
                        .map(String::trim)
                        .filter(s -> s.contains("="))
                        .forEach(s -> {
                            String[] split = s.split("=");
                            properties.setProperty(split[0], split[1]);
                        });
                properties.store(fileOutputStream, null);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}