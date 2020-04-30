package stepdefs;

import cucumber.api.java.ru.Тогда;
import helpers.DataFileHelper;
import helpers.TestHelper;
import io.cucumber.datatable.DataTable;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Для работы с файлами
 */
public class FileSteps {
    private static Logger logger = LoggerFactory.getLogger(FileSteps.class);

    @Тогда("^пользователь заполняет ([^\"]*) файл \"([^\"]*)\" данными$")
    public static void createFileWithData(String fileType, String fileName, DataTable dataTable) {
        String filePath = "target/" + fileName.trim() + "." + fileType.trim();
        logger.info("пользователь заполняет файл " + fileName + "." + fileType + " данными");
        if ("JSON".equals(fileType.toUpperCase())) {
            writeJsonFile(dataTable, filePath);
        }
    }

    @Тогда("^пользователь сохраняет файл \"([^\"]*)\" в каталог \"([^\"]*)\" c данными$")
    public static void createFileToPathWithData(String fileName, String catalogPath, DataTable dataTable) {

        String fullPath = catalogPath.trim() + "/" + fileName.trim();
        logger.info("пользователь сохраняет файл " + fileName + " в каталог " + catalogPath + " c данными");
        if ("JSON".equals(FilenameUtils.getExtension(fileName).toUpperCase())) {
            writeJsonFile(dataTable, fullPath);
        }
    }

    @Тогда("^пользователь читает ([^\"]*) файл \"([^\"]*)\" находящийся в каталоге \"([^\"]*)\"")
    public static void readFileInCatalog(String fileType, String fileName, String filePath) {
        String fullFilePath = filePath.trim() + "/" + fileName.trim() + "." + fileType.trim();
        logger.info("пользователь читает файл " + fullFilePath);
        if ("JSON".equals(fileType.toUpperCase())) {
            readJsonFileWithPath(fullFilePath);
        }
    }

    @Тогда("^пользователь читает файл \"([^\"]*)\" в каталоге \"([^\"]*)\"$")
    public static void readFileFromPath(String fileName, String catalogPath) {

        String fullPath = catalogPath.trim() + "/" + fileName.trim();

        logger.info("пользователь читает файл " + fullPath);
        if ("JSON".equals(FilenameUtils.getExtension(fullPath).toUpperCase()))
            readJsonFileWithPath(fullPath);
    }

    private static void writeJsonFile(DataTable dataTable, String filePath) {
        List<List<String>> table = dataTable.cells();
        JSONObject jsonObject = new JSONObject();
        for (List list : table) {
            jsonObject.put(list.get(0).toString(), list.get(1).toString());
        }
        DataFileHelper.write(filePath, jsonObject.toJSONString());
    }

    private static void readJsonFileWithPath(String fullFilePath) {
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) JSONValue.parseWithException(DataFileHelper.read(fullFilePath));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        TestHelper.setLastJsonData(jsonObject);
    }

}