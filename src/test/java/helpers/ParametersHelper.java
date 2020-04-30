package helpers;

import ru.sbtqa.tag.qautils.properties.Props;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для работы с параметрами
 */
public class ParametersHelper {

    private static HashMap<String, String> testJvmArguments = new HashMap<String, String>();

    /**
     * Метод получения тестовых jvm параметров
     *
     * @return получение List<String> с ключом "-Dautotest"
     */
    public static HashMap<String, String> getTestJvmParameters() {
        if (testJvmArguments.isEmpty()) {
            setTestJvmParameters();
        }
        return testJvmArguments;
    }

    /**
     * Метод для получения jvm параметров
     *
     * @return получение List<String> с ключом "-Dautotest"
     */
    private static List<String> getTestParametersFromJvmOptions() {
        List<String> inputArguments;
        inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        return inputArguments.stream().filter(s -> s.contains("-Dautotest"))
                .map(s -> s.substring(2))
                .collect(Collectors.toList());
    }


    /**
     * Сохранение jvm параметров в HashMap
     */
    private static void setTestJvmParameters() {
        List<String> arguments = getTestParametersFromJvmOptions();
        arguments.stream().forEach(s -> {
            String[] split = s.split("=");
            testJvmArguments.put(split[0], split[1]);
        });
    }

    public static Long getDefaultTimeoutInSeconds() {
        return Long.valueOf(Props.get("page.load.timeout")) / 1000;
    }
}
